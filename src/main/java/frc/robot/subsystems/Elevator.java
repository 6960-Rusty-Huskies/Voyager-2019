package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveElevatorTeleop;

/**
 * The pneumatic actuators that lift the robot to get us to level 3.
 */
public class Elevator extends Subsystem {
  private Spark elevatorMotorLeft;
  private Spark elevatorMotorRight;
  private Spark stabilizerMotor;

  public Elevator() {
    elevatorMotorLeft = new Spark(RobotMap.ELEVATOR_CHANNEL_LEFT);
    elevatorMotorLeft.setInverted(true);
    elevatorMotorRight = new Spark(RobotMap.ELEVATOR_CHANNEL_RIGHT);

    stabilizerMotor = new Spark(RobotMap.STABILIZER_CHANNEL);
  }

  public void setElevator(double speed) {
    elevatorMotorRight.set(speed);
    
    elevatorMotorLeft.set(0.95 * speed);
  }

  public void setStabilizer(double speed) {
    stabilizerMotor.set(0.6 * speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveElevatorTeleop());
  }
}
