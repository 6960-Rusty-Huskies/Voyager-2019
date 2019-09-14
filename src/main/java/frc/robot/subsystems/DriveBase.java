package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveTeleop;

/**
 * The base of the system that moves the robot.
 */
public class DriveBase extends Subsystem {

  private SpeedController leftMotor;
  private SpeedController rightMotor;

  public DifferentialDrive drive;

  public DriveBase(SpeedController left, SpeedController right) {
    leftMotor = left;
    rightMotor = right;
    drive = new DifferentialDrive(leftMotor, rightMotor);

    rightMotor.setInverted(true);
  }

  public void manualDrive(double speed, double turn) {
    if (Math.abs(speed) < 0.1)
      speed = 0;
    if (Math.abs(turn) < 0.1)
      turn = 0;

    drive.arcadeDrive(speed, turn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTeleop());
  }
}
