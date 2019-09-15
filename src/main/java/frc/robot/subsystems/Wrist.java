package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Brake;
import frc.robot.RobotMap;

/**
 * Smaller appendage attached to the arm that holds the claw.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax motor;
  private CANEncoder encoder;
  private double lastPos;

  public Wrist(MotorType motorType) {
    motor = new CANSparkMax(RobotMap.WRIST_CAN_ID, motorType);
    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(RobotMap.WRIST_GEAR_RATIO / 360);
    lastPos = getAngle();
  }

  public void setMotor(double speed) {
    motor.set(speed);
    lastPos = getAngle();
  }

  public void moveTo(double degrees) {
    double diff = degrees - getAngle();
    
    setMotor(diff / 360);
    lastPos = getAngle();
  }

  public double getAngle() {
    return encoder.getPosition();
  }

  public void tuck() {
    if (isTucked())
      return;

    setMotor(-0.5);
    lastPos = getAngle();
  }

  public boolean isTucked() {
    return encoder.getPosition() < 5;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Brake(this, motor, 0.15, lastPos, getAngle()));
  }
}
