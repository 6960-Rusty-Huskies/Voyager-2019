package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveWristTeleop;

/**
 * The smaller appendage attached to the end of the Arm, which holds the Claw.
 */
public class Wrist extends PIDSubsystem {

  // todo: these numbers all have to be verified... currently just a rough estimate
  private static final double SAFE_WRIST_ANGLE_LOW = 20.0;
  private static final double SAFE_WRIST_ANGLE_HIGH = 60.0;
  // This would be 180 if we had actual angle
  private static final double WRIST_STRAIGHT_ANGLE = 40.0;

  private CANSparkMax motor;
  private CANEncoder encoder;

  /**
   * The smaller appendage attached to the end of the Arm, which holds the Claw.
   */
  public Wrist() {
    super("Wrist", .5, 0., 0.);
    motor = new CANSparkMax(RobotMap.WRIST_CAN_ID, MotorType.kBrushless);
    encoder = motor.getEncoder(EncoderType.kHallSensor, 42);
    encoder.setPositionConversionFactor(360. / (encoder.getCPR() * RobotMap.WRIST_GEAR_RATIO));

    setAbsoluteTolerance(5.);
    setInputRange(0., 270.);
    setOutputRange(-0.2, 0.2);
    enable();
  }

  public void setMotor(double speed) {
    motor.set(speed * .15);
  }

  public void moveTo(double degrees) {
    setSetpoint(degrees);
  }

  public double getAngle() {
    return encoder.getPosition();
  }

  public void tuck() {
    setSetpoint(0.);
  }

  public boolean isTucked() {
    return getSetpoint() == 0. && onTarget();
  }

  public double nearestWristSafePosition(double currentWristAngle) {
    if (currentWristAngle <= WRIST_STRAIGHT_ANGLE) {
      return Math.min(currentWristAngle, SAFE_WRIST_ANGLE_LOW);
    } else {
      return Math.max(currentWristAngle, SAFE_WRIST_ANGLE_HIGH);
    }
  }

  public boolean wristNeedsToFlip(double currentWristAngle, double wristAngleGoal) {
    return (currentWristAngle < WRIST_STRAIGHT_ANGLE && wristAngleGoal >= WRIST_STRAIGHT_ANGLE)
            || (currentWristAngle > WRIST_STRAIGHT_ANGLE && wristAngleGoal <= WRIST_STRAIGHT_ANGLE);
  }

  /**
   * Use this to check to see if we are moving into an unsafe position... only useful if Arm is in the unsafe zone.
   *
   * @param wristAngleProjection - Projected wrist angle/where wrist is moving to next.
   * @return Whether or not the wrist will be moving into an unsafe position.
   */
  public boolean isMovingToUnsafePosition(double wristAngleProjection) {
    return (wristAngleProjection > SAFE_WRIST_ANGLE_LOW && wristAngleProjection < SAFE_WRIST_ANGLE_HIGH);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveWristTeleop());
  }

  @Override
  protected double returnPIDInput() {
    return getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    motor.pidWrite(output);
  }
}
