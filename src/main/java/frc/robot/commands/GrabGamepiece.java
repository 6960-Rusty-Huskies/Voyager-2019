package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.utils.Gamepiece;

public class GrabGamepiece extends CommandGroup {

  /**
   * Moves the arm to the level needed to grab the specified gamepiece from the
   * human player station.
   */
  public GrabGamepiece(Gamepiece gamepiece) {
    double height;
    if(gamepiece == Gamepiece.cargo) height = 19.0;
    else height = 42.;

    double armAngleGoal = 360. - Math.toDegrees(Math.acos((RobotMap.ARM_HEIGHT - height) / RobotMap.ARM_RADIUS));
    double wristAngleGoal = armAngleGoal + 90.;

    addParallel(new SetClaw(false));
    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
    
    if (gamepiece == Gamepiece.cargo)
      addSequential(new SetClaw(true));
  }
}
