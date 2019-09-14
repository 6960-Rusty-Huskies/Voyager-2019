package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ToggleClaw;

/**
 * The pneumatic hooks that hold cargo and hatches.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid solenoid;

  public Claw(int forwardChannel, int reverseChannel) {
    solenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    solenoid.set(Value.kForward);
  }

  public void toggle() {
    if(solenoid.get() == Value.kForward)
      solenoid.set(Value.kReverse);
    else solenoid.set(Value.kForward);
  }

  public void open() {
    solenoid.set(Value.kForward);
  }

  public void close() {
    solenoid.set(Value.kReverse);
  }

  public boolean isOpen() {
    return solenoid.get() == Value.kForward;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ToggleClaw());
  }
}
