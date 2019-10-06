package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveElevatorTeleop;

/**
 * The pneumatic actuators that lift the robot to get us to level 3.
 */
public class Elevator extends Subsystem {
  private Spark elevatorMotors;
  private Spark stabilizerMotor;

  public Elevator() {
    elevatorMotors = new Spark(RobotMap.ELEVATOR_CHANNEL);
    stabilizerMotor = new Spark(RobotMap.STABILIZER_CHANNEL);
  }

  public void setElevator(double speed) {
    elevatorMotors.set(speed);
  }

  public void setStabilizer(double speed) {
    stabilizerMotor.set(0.6 * speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveElevatorTeleop());
  }
}