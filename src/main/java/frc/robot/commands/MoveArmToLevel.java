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

        // Check to see if we need to "tuck" the wrist so we don't go out of bounds
        double currentArmAngle = Robot.arm.getAngle();
        double currentWristAngle = Robot.wrist.getAngle();
        if (Robot.arm.isWithinSafeZone(currentArmAngle)) {
            // We are safe to move the wrist here... now check to see if we need to tuck it
            if (Robot.arm.isMovingThroughUnsafeZone(currentArmAngle, armAngleGoal)) {
                addSequential(new MoveArmToAngle(Robot.arm.nearestSafePositionToGoal(currentArmAngle, armAngleGoal)));
            } else {
                // Safe to just move wrist to final angle while arm moves to position
                addParallel(new MoveWristToAngle(wristAngleGoal));
            }
        } else {
            // We are NOT in a safe zone
            addParallel(new MoveWristToAngle(Robot.wrist.nearestWristSafePosition(currentWristAngle)));
            if (Robot.wrist.wristNeedsToFlip(currentWristAngle, wristAngleGoal)) {
                addSequential(new MoveArmToAngle(Robot.arm.nearestSafePositionToGoal(currentArmAngle, armAngleGoal)));
                addSequential(new MoveWristToAngle(Robot.wrist.nearestWristSafePosition(wristAngleGoal)));
            }
        }

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