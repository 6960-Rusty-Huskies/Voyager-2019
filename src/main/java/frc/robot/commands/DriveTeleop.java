package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTeleop extends Command {
  public DriveTeleop() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    
    double speed = Robot.oi.driverStickLeft.getY();
    double turn = Robot.oi.driverStickRight.getX();

    Robot.driveBase.manualDrive(speed, turn);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
    end();
  }
}
