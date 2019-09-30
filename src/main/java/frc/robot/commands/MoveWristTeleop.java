package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWristTeleop extends Command {

  public MoveWristTeleop() {
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double wristStickValue = Robot.oi.operatorStickLeft.getX();

    if (Math.abs(wristStickValue) > 0.1) {

      if (Robot.wrist.getPIDController().isEnabled()) {
        Robot.wrist.disable();
        
      Robot.wrist.setMotor(wristStickValue * 0.2);
      }

    } else if (!Robot.wrist.getPIDController().isEnabled()) {

      Robot.wrist.enable();
      Robot.wrist.setSetpoint(Robot.wrist.getAngle());
      
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
