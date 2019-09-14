/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.Robot;
import frc.robot.utils.Axis;

public class AutoSwitchCamera extends Command {

  private Accelerometer accelerometer;
  private Axis axis;

  public AutoSwitchCamera(Axis axisOfMotion) {
    requires(Robot.vision);
    axis = axisOfMotion;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double val = 0;

    switch(axis) {
    case x:
      val = accelerometer.getX();
      break;
    case y:
      val = accelerometer.getY();
      break;
    case z:
      val = accelerometer.getZ();
      break;
    }

    if (val > 0.1) {
      Robot.vision.setFrontCamera();
    } else if (accelerometer.getX() < -0.1) {
      Robot.vision.setBackCamera();
    } else
      Robot.vision.setFrontCamera();
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
