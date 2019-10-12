package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class MoveWristToAngle extends Command {

  private double angleGoal;

  public MoveWristToAngle(double degrees) {
    requires(Voyager.wrist);
    angleGoal = degrees;
  }

  @Override
  protected void initialize() {
    Voyager.wrist.moveTo(angleGoal);
    System.out.println("##### MoveWristToAngle(" + angleGoal + ") initialized. #####");
  }

  @Override
  protected boolean isFinished() {
    return Voyager.wrist.onTarget();
  }

  @Override
  protected void end() {
    System.out.println("##### MoveWristToAngle(" + angleGoal + ") ended. #####");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
