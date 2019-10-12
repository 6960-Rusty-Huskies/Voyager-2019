package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.rustyhuskies.Voyager;

public class SetClaw extends InstantCommand {

  private boolean toSet;

  public SetClaw(boolean open) {
    requires(Voyager.claw);
    toSet = open;
  }

  @Override
  protected void execute() {
    Voyager.claw.set(toSet);
  }
}
