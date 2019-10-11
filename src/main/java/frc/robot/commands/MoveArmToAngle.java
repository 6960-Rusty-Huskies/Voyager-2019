package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmToAngle extends Command {

  private double angleGoal;

  public MoveArmToAngle(double degrees) {
    requires(Robot.arm);
    angleGoal = degrees;
  }

  @Override
  protected void initialize() {
    Robot.arm.moveTo(angleGoal);
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.onTarget();
  }

  @Override
  protected void end(){

  }

  @Override
  protected void interrupted() {
    end();
  }
}
