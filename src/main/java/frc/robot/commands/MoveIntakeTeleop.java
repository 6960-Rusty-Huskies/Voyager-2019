package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveIntakeTeleop extends Command {
  public MoveIntakeTeleop() {
    requires(Robot.intake);
  }

  @Override
  protected void execute() {
    Robot.intake.moveArm(0.7 * Robot.oi.operatorStickRight.getX());
    Robot.intake.moveRoller(Robot.oi.operatorStickRight.getY());
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
