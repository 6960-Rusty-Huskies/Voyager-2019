package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class DriveTeleop extends Command {
  public DriveTeleop() {
    requires(Voyager.driveBase);
  }

  @Override
  protected void execute() {
    Voyager.driveBase.manualDrive(Voyager.oi.driverStickLeft.getY(), Voyager.oi.driverStickRight.getX());
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
