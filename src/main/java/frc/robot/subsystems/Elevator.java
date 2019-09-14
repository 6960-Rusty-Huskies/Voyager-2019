package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The pneumatic actuators that lift the robot to get us to level 3.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid leftSolenoid;
  private DoubleSolenoid rightSolenoid;

  public Elevator(int leftForwardId, int leftReverseId, int rightForwardId, int rightReverseId) {
    leftSolenoid = new DoubleSolenoid(leftForwardId, leftReverseId);
    rightSolenoid = new DoubleSolenoid(rightForwardId, rightReverseId);

    leftSolenoid.set(Value.kReverse);
    rightSolenoid.set(Value.kReverse);
  }

  public void toggle() {
    Value toggleValue = leftSolenoid.get() == Value.kForward ? Value.kReverse : Value.kForward;

    leftSolenoid.set(toggleValue);
    rightSolenoid.set(toggleValue);
  }

  public void putDown() {
    leftSolenoid.set(Value.kForward);
    rightSolenoid.set(Value.kForward);
  }

  public void putUp() {
    leftSolenoid.set(Value.kReverse);
    rightSolenoid.set(Value.kReverse);
  }

  public Value value() {
    return leftSolenoid.get();
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new ToggleElevator());
  }
}