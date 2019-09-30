package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTeleop extends Command {

  public DriveTeleop() {
    requires(Robot.driveBase);
  }

  @Override
  protected void execute() {
    Robot.driveBase.manualDrive(Robot.oi.driverStickLeft.getY(), Robot.oi.driverStickRight.getX());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
