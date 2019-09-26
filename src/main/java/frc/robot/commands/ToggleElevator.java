package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleElevator extends InstantCommand {

  public ToggleElevator() {
    requires(Robot.elevator);
  }

  // Called once this Command executes
  @Override
  protected void initialize() {
    Robot.elevator.toggle();
  }
}
