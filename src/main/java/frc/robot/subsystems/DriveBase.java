package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTeleop;

/**
 * A base with wheels that moves the robot.
 */
public class DriveBase extends Subsystem {

  private SpeedController leftMotor;
  private SpeedController rightMotor;
  private DifferentialDrive drive;

  public DriveBase() {
    Spark leftTop = new Spark(RobotMap.DRIVE_LEFT_TOP_CHANNEL);
    Spark leftFront = new Spark(RobotMap.DRIVE_LEFT_FRONT_CHANNEL);
    Spark leftBack = new Spark(RobotMap.DRIVE_LEFT_BACK_CHANNEL);
    SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftTop, leftFront, leftBack);

    Spark rightTop = new Spark(RobotMap.DRIVE_RIGHT_TOP_CHANNEL);
    Spark rightFront = new Spark(RobotMap.DRIVE_RIGHT_FRONT_CHANNEL);
    Spark rightBack = new Spark(RobotMap.DRIVE_RIGHT_BACK_CHANNEL);
    SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightTop, rightFront, rightBack);

    leftMotor = leftGroup;
    rightMotor = rightGroup;

    drive = new DifferentialDrive(leftMotor, rightMotor);
  }

  public void manualDrive(double speed, double turn) {
    if (Math.abs(speed) < 0.1)
      speed = 0;
    if (Math.abs(turn) < 0.1)
      turn = 0;

    drive.arcadeDrive(0.6 * speed, 0.6 * turn, true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTeleop());
  }
}
