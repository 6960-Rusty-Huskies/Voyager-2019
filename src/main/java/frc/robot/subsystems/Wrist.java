package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Brake;

/**
 * Smaller appendage attached to the arm that holds the claw.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax motor;
  private CANEncoder encoder;
  private double lastPos;
  CANPIDController controller;

  public Wrist(int deviceId, MotorType type, double gearRatio) {
    motor = new CANSparkMax(deviceId, type);
    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(gearRatio / 360);
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
