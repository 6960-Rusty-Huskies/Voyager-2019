package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmTeleop extends Command {

  private double armStickValue = Robot.oi.operatorStickLeft.getY();

  public MoveArmTeleop() {
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (armStickValue > 0.1) {

      if (Robot.arm.getPIDController().isEnabled()) {
        Robot.arm.disable();
      }

      Robot.arm.setMotor(Math.signum(armStickValue) * 0.2);

      if (armStickValue == 0) {
        Robot.arm.setSetpoint(Robot.arm.getAngle());
      }

    } else if (!Robot.arm.getPIDController().isEnabled()) {

      Robot.wrist.setSetpoint(Robot.wrist.getAngle());
      Robot.wrist.enable();
      
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
