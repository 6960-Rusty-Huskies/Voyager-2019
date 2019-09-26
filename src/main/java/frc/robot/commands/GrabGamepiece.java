package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.utils.Gamepiece;

public class GrabGamepiece extends CommandGroup {
  private static final double armRadius = RobotMap.ARM_RADIUS;
  private static final double armHeight = RobotMap.ARM_HEIGHT;

  /**
   * Moves the arm to the level needed to grab the specified gamepiece from the human player station.
   */
  public GrabGamepiece(Gamepiece gamepiece) {
    double height;
    if(gamepiece == Gamepiece.cargo) height = 19.0;
    else height = 42.;

    double armAngleGoal = 360. - Math.toDegrees(Math.acos((armHeight - height) / armRadius));
    double wristAngleGoal = armAngleGoal + 90.;

    addParallel(new SetClaw(false));
    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
  }
}
