package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;

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
                switch(gamepiece) {
                    case cargo:
                        armAngleGoal = 45.;
                        wristAngleGoal = 70.;
                        break;
                    case hatch: 
                        armAngleGoal = 40.;
                        wristAngleGoal = 90.;
                        break;
                }
            break;
            case middle:
                switch(gamepiece) {
                    case cargo:
                        armAngleGoal = 81.;
                        wristAngleGoal = 78.;
                        break;
                    case hatch:
                        armAngleGoal = Robot.arm.getAngle();
                        wristAngleGoal = Robot.wrist.getAngle();
                        break;
                }
            break;
            case high:
                switch(gamepiece) {
                    case cargo:
                        armAngleGoal = 168.;
                        wristAngleGoal = 197.;
                        break;
                    case hatch:
                        armAngleGoal = 170.;
                        wristAngleGoal = 215.;
                        break;
                }
            break;
        }

        // // Check to see if we need to "tuck" the wrist so we don't go out of bounds
        // double currentArmAngle = Robot.arm.getAngle();
        // double currentWristAngle = Robot.wrist.getAngle();
        // if (Robot.arm.isWithinSafeZone(currentArmAngle)) {
        //     // We are safe to move the wrist here... now check to see if we need to tuck it
        //     if (Robot.arm.isMovingThroughUnsafeZone(currentArmAngle, armAngleGoal)) {
        //         addSequential(new MoveArmToAngle(Robot.arm.nearestSafePositionToGoal(currentArmAngle, armAngleGoal)));
        //     } else {
                // Safe to just move wrist to final angle while arm moves to position
                // addParallel(new MoveWristToAngle(wristAngleGoal));
        //     }
        // } else {
        //     // We are NOT in a safe zone
        //     addParallel(new MoveWristToAngle(Robot.wrist.nearestWristSafePosition(currentWristAngle)));
        //     if (Robot.wrist.wristNeedsToFlip(currentWristAngle, wristAngleGoal)) {
        //         addSequential(new MoveArmToAngle(Robot.arm.nearestSafePositionToGoal(currentArmAngle, armAngleGoal)));
        //         addSequential(new MoveWristToAngle(Robot.wrist.nearestWristSafePosition(wristAngleGoal)));
        //     }
        // }

        addSequential(new MoveArmToAngle(armAngleGoal));
        addSequential(new MoveWristToAngle(wristAngleGoal));
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || Robot.oi.operatorStickLeft.getMagnitude() > 0.1;
    }
}
