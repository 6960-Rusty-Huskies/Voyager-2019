package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Brake;

/**
 * Smaller appendage attached to the arm that holds the claw.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark motor;
  private Encoder encoder;
  private double lastPos;

  public Wrist(int motorPort, int encoderPortA, int encoderPortB, int encoderPulsesPerRevolution, double gearRatio) {
    motor = new Spark(motorPort);
    encoder = new Encoder(encoderPortA, encoderPortB);
    encoder.setDistancePerPulse(360 / (encoderPulsesPerRevolution / gearRatio));
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
    return encoder.getDistance();
  }

  public void tuck() {
    if (isTucked())
      return;

    setMotor(-0.5);
    lastPos = getAngle();
  }

  public boolean isTucked() {
    return encoder.getDistance() < 5;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Brake(this, motor, 0.15, lastPos, getAngle()));
  }
}
