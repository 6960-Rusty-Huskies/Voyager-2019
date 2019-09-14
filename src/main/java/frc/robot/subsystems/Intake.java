package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The arm opposite to the claw-arm that intakes cargo and helps with the Hab
 * climb.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark base;
  private Spark wheels;

  public Intake(int basePort, int rollerPort) {
    base = new Spark(basePort);
    wheels = new Spark(rollerPort);
  }

  public void moveRoller(double speed) {
    wheels.set(speed);
  }

  public void moveArm(double speed) {
    base.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
