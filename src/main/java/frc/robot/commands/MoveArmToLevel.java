package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;

public class MoveArmToLevel extends CommandGroup {

    /**
     * Moves the arm to the specified level of the rocket, for the specified gamepiece.
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
                break;
            case middle:
                if (gamepiece == Gamepiece.cargo) {
                    armAngleGoal = 81.45;
                    wristAngleGoal = 15.5;
                } else {
                    armAngleGoal = 75.0;
                    wristAngleGoal = 20.0;
                }
                break;
            case high:
                if (gamepiece == Gamepiece.cargo) {
                    armAngleGoal = 161.9;
                    wristAngleGoal = 35.1;
                } else {
                    armAngleGoal = 164.1;
                    wristAngleGoal = 40.5;
                }
                break;
        }

        // Always tuck when the arm moves, then move wrist to proper position after arm gets to final destination
        addParallel(new MoveWristToAngle(2));
        addSequential(new MoveArmToAngle(armAngleGoal));
        addSequential(new MoveWristToAngle(wristAngleGoal));
    }

  @Override
  protected boolean isFinished() {
      if (super.isFinished()) {
        return true;
      } else {
        return Math.round(Robot.oi.operatorStickLeft.getMagnitude()) != 0;
      }
  }
}