package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;

public class MoveArmToLevel extends CommandGroup {

  /**
   * Moves the arm to the specified level of the rocket, for the specified
   * gamepiece.
   */
  public MoveArmToLevel(Level level, Gamepiece gamepiece) {
    double armAngleGoal = 0.0;

    switch (level) {
      case low:
        if(gamepiece == Gamepiece.cargo) armAngleGoal = 63;
        else armAngleGoal = 44;
      case middle:
        if(gamepiece == Gamepiece.cargo) armAngleGoal = 115;
        else armAngleGoal = 99;
      case high:
      armAngleGoal = 0.0;
    }

    double wristAngleGoal = armAngleGoal + 90.;
    while(Math.abs(wristAngleGoal) % 360 > 0) wristAngleGoal += Math.signum(wristAngleGoal) * 360;
    

    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
  }
}