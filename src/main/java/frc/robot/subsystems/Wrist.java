package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveWristTeleop;

/**
 * Add your docs here.
 */
public class Wrist extends PIDSubsystem {

  private CANSparkMax motor;
  private CANEncoder encoder;
  private double lastAngle;

  /**
   * Add your docs here.
   */
  public Wrist(MotorType motorType) {
    super("Wrist", -1 / 360, 0.0, 0.0);
    motor = new CANSparkMax(RobotMap.WRIST_CAN_ID, motorType);
    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(RobotMap.WRIST_GEAR_RATIO / 360);
    lastAngle = getAngle();
  }

  public void setMotor(double speed) {
    motor.set(speed);
    lastAngle = getAngle();
  }

  public void moveTo(double degrees) {
    double diff = degrees - getAngle();
    
    setMotor(diff / 360);
    lastAngle = getAngle();
  }

  public double getAngle() {
    return encoder.getPosition();
  }

  public void tuck() {
    if (isTucked())
      return;

    setMotor(-0.5);
    lastAngle = getAngle();
  }

  public boolean isTucked() {
    return encoder.getPosition() < 5;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveWristTeleop());
  }

  @Override
  protected double returnPIDInput() {
    return encoder.getPosition() - lastAngle;
  }

  @Override
  protected void usePIDOutput(double output) {
    motor.set(output); 
  }
}
