package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Axis;

public class AutoSwitchCamera extends Command {

  private Joystick joystick;
  private Axis axis;

  public AutoSwitchCamera(Joystick stick, Axis axisOfMotion) {
    requires(Robot.vision);
    axis = axisOfMotion;
    joystick = stick;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double val = 0;

    switch(axis) {
    case x:
      val = joystick.getX();
      break;
    case y:
      val = joystick.getY();
      break;
    case z:
      val = joystick.getZ();
      break;
    }

    if (val > 0.1) {
      Robot.vision.setFrontCamera();
    } else if (val < -0.1) {
      Robot.vision.setBackCamera();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
