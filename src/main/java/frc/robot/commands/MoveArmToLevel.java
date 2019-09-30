package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;
import frc.robot.Robot;

public class MoveArmToLevel extends CommandGroup {

  /**
   * Moves the arm to the specified level of the rocket, for the specified
   * gamepiece.
   */
  public MoveArmToLevel(Level level, Gamepiece gamepiece) {
    double armAngleGoal = 0.0;
    double wristAngleGoal = 0.0;

    switch (level) {
    case low:
      if (gamepiece == Gamepiece.cargo) {
        armAngleGoal = 49.5;
        wristAngleGoal = 12.7;
      } else {
        armAngleGoal = 36.4;
        wristAngleGoal = 16.2;
      }
    case middle:
      if (gamepiece == Gamepiece.cargo) {
        armAngleGoal = 81.45;
        wristAngleGoal = 15.5;
      }
      else {
        armAngleGoal = 81.45;
        wristAngleGoal = 15.5;
      }
      case high:
        if(gamepiece == Gamepiece.cargo) {
          armAngleGoal = 161.9;
          wristAngleGoal = 35.1;
        }
        else {
          armAngleGoal = 164.1;
          wristAngleGoal = 40.5;
        }
    }

    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
  }
}