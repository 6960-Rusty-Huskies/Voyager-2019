package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWristTeleop extends Command {

  private long previousStickValue = 0;

  public MoveWristTeleop() {
    requires(Robot.wrist);
  }

  @Override
  protected void execute() {
    long currentStickValue = Math.round(Robot.oi.operatorStickLeft.getX());

    // Check to see if joystick has changed
    if (currentStickValue != previousStickValue) {
      if (currentStickValue != 0) {
        // User is moving wrist, stop PID Controller
        Robot.wrist.disable();
        Robot.wrist.setMotor(currentStickValue * 0.2);
      } else {
        // Stop wrist and enable PID Controller
        Robot.wrist.setMotor(0);
        Robot.wrist.enable();
        Robot.wrist.setSetpoint(Robot.wrist.getAngle());
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
