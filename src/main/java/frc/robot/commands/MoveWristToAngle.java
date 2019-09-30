package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWristToAngle extends Command {

  double angleGoal;

  public MoveWristToAngle(double degrees) {
    requires(Robot.wrist);

    angleGoal = degrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.wrist.moveTo(angleGoal);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.oi.operatorStickLeft.getMagnitude()) > 0.1)
      end();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.wrist.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
