package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmToAngle extends Command {

  private double angleGoal;

  public MoveArmToAngle(double degrees) {
    requires(Robot.arm);
    angleGoal = degrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.arm.moveTo(angleGoal);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(angleGoal - Robot.arm.getAngle()) < 0.5;
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
