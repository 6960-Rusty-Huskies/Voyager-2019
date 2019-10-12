package frc.rustyhuskies.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.rustyhuskies.Voyager;
import frc.rustyhuskies.RobotMap;
import frc.rustyhuskies.commands.MoveWristTeleop;

/**
 * The smaller appendage attached to the end of the Arm, which holds the Claw.
 */
public class Wrist extends PIDSubsystem {

  private CANSparkMax motor;
  private CANEncoder encoder;

  /**
   * The smaller appendage attached to the end of the Arm, which holds the Claw.
   */
  public Wrist() {
    super("Wrist", 0.005, 0., 0.);
    motor = new CANSparkMax(RobotMap.WRIST_CAN_ID, MotorType.kBrushless);
    
    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor((2. * RobotMap.WRIST_GEAR_RATIO * 42.) / 360.);
    encoder.setPosition(0.0);

    setAbsoluteTolerance(0.5);
    setInputRange(0., 270.);
    setOutputRange(-0.3, 0.3);
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

  public double nearestPositionInBoundary() {
    if(isWithinBoundary(getAngle())) {
      return getAngle();
    }
    return Math.acos((30. - getAngle()) / RobotMap.WRIST_RADIUS);
  }

  public boolean isWithinBoundary(double currentAngle) {
    return getXComponent() + Voyager.arm.getXComponent() <= RobotMap.FRAME_PERIMITER + 30.;
  }

  public double getXComponent() {
    return RobotMap.WRIST_RADIUS * Math.cos(Math.toRadians(270. - getAngle() - Voyager.arm.getAngle()));
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
