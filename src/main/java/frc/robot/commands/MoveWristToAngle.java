package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWristToAngle extends Command {

  private double angleGoal;

  public MoveWristToAngle(double degrees) {
    requires(Robot.wrist);
    angleGoal = degrees;
  }

  @Override
  protected void initialize() {
    Robot.wrist.moveTo(angleGoal);
  }

  @Override
  protected boolean isFinished() {
    return Robot.wrist.onTarget();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
