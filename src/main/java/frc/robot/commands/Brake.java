/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Brake extends Command {

  private SpeedController motor;
  private double speedMultiplier;
  private double lastPos;
  private double currentPos;

  public Brake(Subsystem subsystem, SpeedController speedController, double velocityMultiplier, 
      double lastPosition, double currentPosition) {
    requires(subsystem);
    motor = speedController;
    speedMultiplier = velocityMultiplier;
    lastPos = lastPosition;
    currentPos = currentPosition;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double diff = lastPos - currentPos;
    double speed = speedMultiplier * diff;

    if (Math.abs(speed) > 0.3)
      speed = Math.signum(speed) * 0.3;

    motor.set(-speed);
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
