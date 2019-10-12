package frc.rustyhuskies.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.rustyhuskies.RobotMap;
import frc.rustyhuskies.commands.MoveElevatorTeleop;

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
    elevatorMotorRight.set(SmartDashboard.getNumber("DB/Slider 0", 1.0) * speed);
    elevatorMotorLeft.set(SmartDashboard.getNumber("DB/Slider 1", 1.0) * speed);
  }

  public void setStabilizer(double speed) {
    stabilizerMotor.set(0.6 * speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveElevatorTeleop());
  }
}
