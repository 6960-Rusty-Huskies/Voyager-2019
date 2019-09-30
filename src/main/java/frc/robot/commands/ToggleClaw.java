package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleClaw extends InstantCommand {

  public ToggleClaw() {
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.toggle();
  }
}
