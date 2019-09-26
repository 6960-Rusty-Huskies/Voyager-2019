package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArmTeleop;

/**
 * The arm connected to the frame of the robot that holds the wrist.
 */
public class Arm extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private SpeedController motor;
  private Encoder encoder;
  private double lastAngle;

  public Arm(SpeedController speedController) {
    super("Arm", - 1 / 360, 0.0, 0.0);
    setInputRange(-0.5, 0.5);
    this.

    motor = speedController;
    encoder = new Encoder(RobotMap.ARM_ENCODER_A_CHANNEL, RobotMap.ARM_ENCODER_B_CHANNEL);
    encoder.setDistancePerPulse(360 / (RobotMap.ARM_ENCODER_PPR * RobotMap.ARM_GEAR_RATIO));

    lastAngle = getAngle();
  }

  public void setMotor(double speed) {
    motor.set(speed);
    lastAngle = getAngle();
  }

  public void moveTo(double degrees) {
    if (!Robot.wrist.isTucked())
      Robot.wrist.tuck();

    else {
      double diff = degrees - getAngle();
      setMotor(diff / 360);
    }
  }

  public double getAngle() {
    return encoder.getDistance();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArmTeleop());
  }

  @Override
  public double returnPIDInput() {
    return encoder.getDistance() - lastAngle;
  }

  @Override
  public void usePIDOutput(double output) {
    motor.set(output);
  }
}
