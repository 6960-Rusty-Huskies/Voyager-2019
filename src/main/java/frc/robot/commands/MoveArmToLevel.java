package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;

public class MoveArmToLevel extends CommandGroup {

  private double heightGoal;
  private final double armHeight = RobotMap.ARM_HEIGHT;
  private final double armRadius = RobotMap.ARM_RADIUS;

  /**
   * Moves the arm to the specified level of the rocket, for the specified
   * gamepiece.
   */
  public MoveArmToLevel(Level level, Gamepiece gamepiece) {
    double initial = 0;
    int multiplier = 0;
    double spaceBetween = 28.0;

    switch (gamepiece) {
    case cargo:
      initial = 27.5;
    case hatch:
      initial = 19.0;
    }

    switch (level) {
    case low:
      multiplier = 1;
    case middle:
      multiplier = 2;
    case high:
      multiplier = 3;
    }

    heightGoal = initial + multiplier * spaceBetween;

    double armAngleGoal = Math.toDegrees(Math.acos((armHeight - heightGoal) / armRadius));
    double wristAngleGoal = armAngleGoal - 90;

    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
  }
}