package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWristTeleop extends Command {

    private long previousStickValue = 0;

    public MoveWristTeleop() {
        requires(Robot.wrist);
    }

    @Override
    protected void execute() {
        long currentStickValue = Math.round(Robot.oi.operatorStickLeft.getX());

        if (currentStickValue != 0) {
            // We should never be able to get into unsafe zone with wrist in unsafe position...
            //    so we only need to make sure that if we are in unsafe zone, we can't manually move wrist into unsafe pos.

            double currentPosition = Robot.wrist.getAngle();
            // set target to 1 degrees away to check if we can move or not
            double currentTargetHeading = currentPosition + (currentStickValue);
            if ((!Robot.arm.isWithinSafeZone(Robot.arm.getAngle()) && Robot.wrist.isMovingToUnsafePosition(currentTargetHeading))) {
                // Set it to not move as that would put wrist into unsafe position
                currentStickValue = 0;
            }
        }

        // Check to see if joystick has changed
        if (currentStickValue != previousStickValue) {
            if (currentStickValue != 0) {
                // User is moving wrist, stop PID Controller
                Robot.wrist.disable();
                Robot.wrist.setMotor(currentStickValue);
            } else {
                // Stop wrist and enable PID Controller
                Robot.wrist.setMotor(0);
                Robot.wrist.enable();
                Robot.wrist.setSetpoint(Robot.wrist.getAngle());
            }
            previousStickValue = currentStickValue;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        end();
    }
}
