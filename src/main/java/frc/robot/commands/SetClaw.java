package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SetClaw extends InstantCommand {

  private boolean toSet;

  public SetClaw(boolean open) {
    requires(Robot.claw);
    toSet = open;
  }

  // Called once this Command executes
  @Override
  protected void execute() {
    Robot.claw.set(toSet);
  }
}
