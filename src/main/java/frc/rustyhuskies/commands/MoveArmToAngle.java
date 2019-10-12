package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class MoveArmToAngle extends Command {

  private double angleGoal;

  public MoveArmToAngle(double degrees) {
    requires(Voyager.arm);
    angleGoal = degrees;
  }

  @Override
  protected void initialize() {
    Voyager.arm.moveTo(angleGoal);
    System.out.println("##### MoveArmToAngle(" + angleGoal + ") initialized. #####");
  }

  @Override
  protected boolean isFinished() {
    return Voyager.arm.onTarget();
  }

  @Override
  protected void end(){
    System.out.println("##### MoveArmToAngle(" + angleGoal +") ended. #####");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
