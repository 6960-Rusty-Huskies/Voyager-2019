package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmTeleop extends Command {

    private long previousStickValue = 0;

    public MoveArmTeleop() {
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        long currentStickValue = Math.round(Robot.oi.operatorStickLeft.getY());

        if (currentStickValue != 0) {
            // Check to make sure wrist is tucked if moving through an unsafe zone
            double currentPosition = Robot.arm.getAngle();
            // set target to 3 degrees away as to give time to tuck wrist if needed
            double currentTargetHeading = currentPosition + (currentStickValue * 3);
            if (Robot.arm.isWithinSafeZone(currentPosition) && !Robot.arm.isWithinSafeZone(currentTargetHeading)) {
                new MoveWristToAngle(Robot.wrist.nearestWristSafePosition(Robot.wrist.getAngle())).start();
            }
        }

        // Check to see if joystick has changed
        if (currentStickValue != previousStickValue) {
            if (currentStickValue != 0) {
                // User is moving arm, stop PID Controller
                Robot.arm.disable();
                Robot.arm.setMotor(currentStickValue);
            } else {
                // Stop arm and enable PID Controller
                Robot.arm.setMotor(0);
                Robot.arm.enable();
                Robot.arm.setSetpoint(Robot.arm.getAngle());
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
