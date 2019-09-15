package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The arm opposite to the claw-arm that intakes cargo and helps with the Hab
 * climb.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark base;
  private Spark roller;

  public Intake() {
    base = new Spark(RobotMap.INTAKE_BASE_PORT_CHANNEL);
    roller = new Spark(RobotMap.INTAKE_ROLLER_PORT_CHANNEL);
  }

  public void moveRoller(double speed) {
    roller.set(speed);
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
