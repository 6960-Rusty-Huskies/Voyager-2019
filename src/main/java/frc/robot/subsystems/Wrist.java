package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveWristTeleop;

/**
 * The small appendage attached to the end of the Arm which holds the Claw.
 */
public class Wrist extends PIDSubsystem {

  private CANSparkMax motor;
  private CANEncoder encoder;

  /**
   * The smaller appendage attached to the end of the arm which holds the claw.
   */
  public Wrist() {
    super("Wrist", 2.0, 0.0, 0.0);
    setInputRange(0.0, 360.0);
    setAbsoluteTolerance(0.5);
    enable();
    getPIDController().setContinuous();

    motor = new CANSparkMax(RobotMap.WRIST_CAN_ID, MotorType.kBrushless);

    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(RobotMap.WRIST_GEAR_RATIO / 360.0);
    encoder.setAverageDepth(7);
    encoder.setPosition(0);
  }

  public void setMotor(double speed) {
    motor.set(speed);
  }

  public void moveTo(double degrees) {
    setSetpoint(degrees);
  }

  /** The current angle reading of the encoder. */
  public double getAngle() {
    return encoder.getPosition();
  }

  public void tuck() {
    if (isTucked())
      return;

    setSetpoint(0.0);
  }

  public boolean isTucked() {
    return getSetpoint() == 0.0;
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
