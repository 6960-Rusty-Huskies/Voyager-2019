package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The pneumatic hooks that hold cargo and hatches.
 */
public class Claw extends Subsystem {
  private DoubleSolenoid solenoid;

  public Claw() {
    solenoid = new DoubleSolenoid(RobotMap.CLAW_FORWARD_CHANNEL, RobotMap.CLAW_REVERSE_CHANNEL);
    solenoid.set(Value.kReverse);
  }

  public void toggle() {
    if (solenoid.get() == Value.kForward)
      solenoid.set(Value.kReverse);
    else
      solenoid.set(Value.kForward);
  }

  public void set(boolean open) {
    solenoid.set(open ? Value.kForward : Value.kReverse);
  }

  public boolean isOpen() {
    return solenoid.get() == Value.kForward;
  }

  @Override
  public void initDefaultCommand() {
  }
}
