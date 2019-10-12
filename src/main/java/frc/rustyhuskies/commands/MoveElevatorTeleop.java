/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class MoveElevatorTeleop extends Command {
  public MoveElevatorTeleop() {
    requires(Voyager.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double elevatorSpeed = (double) Math.round(Voyager.oi.operatorStickRight.getX());
    boolean moveStabilizerDown = Voyager.oi.driverStickLeft.getRawButton(10);
    boolean moveStabilizerUp = Voyager.oi.driverStickLeft.getRawButton(11);

    Voyager.elevator.setElevator(elevatorSpeed);

    if (moveStabilizerDown)
      Voyager.elevator.setStabilizer(1.0);
    else if(moveStabilizerUp) 
      Voyager.elevator.setStabilizer(-1.0);
    else 
      Voyager.elevator.setStabilizer(0.0);
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
  }
}
