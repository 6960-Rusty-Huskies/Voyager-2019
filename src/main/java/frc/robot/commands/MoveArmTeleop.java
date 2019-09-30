package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmTeleop extends Command {

  private long previousStickValue = 0;

  public MoveArmTeleop() {
    requires(Robot.arm);
  }

  @Override
  protected void execute() {
    long currentStickValue = Math.round(Robot.oi.operatorStickLeft.getY());

    // Check to see if joystick has changed
    if (currentStickValue != previousStickValue) {
      if (currentStickValue != 0) {
        // User is moving arm, stop PID Controller
        Robot.arm.disable();
        Robot.arm.setMotor(currentStickValue);
      } else {
        // Stop arm and enable PID Controller
        Robot.arm.setMotor(0);
        Robot.arm.enable();
        Robot.arm.setSetpoint(Robot.arm.getAngle());
      }
      previousStickValue = currentStickValue;
    }
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
