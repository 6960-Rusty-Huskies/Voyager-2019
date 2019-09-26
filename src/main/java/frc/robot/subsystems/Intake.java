package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveIntakeTeleop;

/**
 * The arm opposite to the claw-arm that intakes cargo and helps with the Hab
 * climb.
 */
public class Intake extends Subsystem {
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
    setDefaultCommand(new MoveIntakeTeleop());
  }
}
