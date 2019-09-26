/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;
import frc.robot.RobotMap;

public class MoveArmToLevel extends CommandGroup {

  private double heightGoal;
  private final double armHeight = RobotMap.ARM_HEIGHT;
  private final double armRadius = RobotMap.ARM_RADIUS;

  /**
   * Moves the arm to the specified level of the rocket, for the specified gamepiece.
   */
  public MoveArmToLevel(Level level, Gamepiece gamepiece) {
    double initial = 0.;
    int multiplier = 0;
    double spaceBetween = 28.;

    switch (gamepiece) {
      case cargo: initial = 27.5;
      case hatch: initial = 19.;
    }

    switch(level) {
      case low: multiplier = 1;
      case medium: multiplier = 2;
      case high: multiplier = 3;
    }

    heightGoal = initial + multiplier * spaceBetween;

    double armAngleGoal = Math.toDegrees(Math.acos((armHeight - heightGoal) / armRadius));
    double wristAngleGoal = armAngleGoal - 90.;

    addSequential(new MoveArmToAngle(armAngleGoal));
    addSequential(new MoveWristToAngle(wristAngleGoal));
  }
}
