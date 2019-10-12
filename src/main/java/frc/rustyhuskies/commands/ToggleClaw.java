package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.rustyhuskies.Voyager;

public class ToggleClaw extends InstantCommand {

  public ToggleClaw() {
    requires(Voyager.claw);
  }

  @Override
  protected void initialize() {
    Voyager.claw.toggle();
  }
}
