package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArmTeleop;

/**
 * The arm connected to the frame of the robot that holds the wrist.
 */
public class Arm extends PIDSubsystem {
  private SpeedController motor;
  private Encoder encoder;

  /**
   * The arm connected to the frame of the robot that holds the wrist.
   */
  public Arm() {
    super("Arm", 2., 0., 0.);

    encoder = new Encoder(RobotMap.ARM_ENCODER_A_CHANNEL, RobotMap.ARM_ENCODER_B_CHANNEL);
    encoder.setDistancePerPulse(360. / (RobotMap.ARM_ENCODER_PPR * RobotMap.ARM_GEAR_RATIO));
    encoder.setSamplesToAverage(7);
    encoder.setReverseDirection(true);

    Spark top = new Spark(RobotMap.ARM_MOTOR_TOP_CHANNEL);
    Spark bottom = new Spark(RobotMap.ARM_MOTOR_BOTTOM_CHANNEL);

    motor = new SpeedControllerGroup(top, bottom);

    setAbsoluteTolerance(5.);
    setInputRange(0., 360.);
    setOutputRange(-0.5, 0.5);
    getPIDController().setContinuous();
    enable();
  }

  public void setMotor(double speed) {
    if (!Robot.wrist.isTucked()) {
      Robot.wrist.tuck();
    }
    motor.set(speed);
  }

  public void moveTo(double degrees) {
    if (!Robot.wrist.isTucked()) {
      Robot.wrist.tuck();
    } else {
      setSetpoint(degrees);
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
    return getAngle() - 180.;
  }

  @Override
  public void usePIDOutput(double output) {
    motor.pidWrite(output);
  }
}
