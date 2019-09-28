package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmTeleop extends Command {
  private Joystick operatorStickLeft = Robot.oi.operatorStickLeft;

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
    if (Math.abs(operatorStickLeft.getY()) > 0.1) {

      if (Robot.arm.getPIDController().isEnabled()) {
        Robot.arm.disable();
      } else {
        Robot.arm.setMotor(operatorStickLeft.getY());
      }

    }

    else if (!Robot.arm.getPIDController().isEnabled()) {
      
      Robot.arm.enable();
      Robot.arm.setSetpoint(Robot.arm.getAngle());
      
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
