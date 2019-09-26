package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTeleop extends Command {

  private double speed = Robot.oi.driverStickLeft.getY();
  private double turn = Robot.oi.driverStickRight.getX();

  public DriveTeleop() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {

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
