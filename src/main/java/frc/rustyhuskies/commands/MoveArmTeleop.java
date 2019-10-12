package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class MoveArmTeleop extends Command {

    private long previousStickValue = 0;

    public MoveArmTeleop() {
        requires(Voyager.arm);
    }

    @Override
    protected void execute() {
        long currentStickValue = Math.round(Voyager.oi.operatorStickLeft.getY());

        // Check to see if joystick has changed
        if (currentStickValue != previousStickValue) {
            if (currentStickValue != 0) {
                // User is moving arm, stop PID Controller
                Voyager.arm.disable();
                Voyager.arm.setMotor(currentStickValue);
            } else {
                // Stop arm and enable PID Controller
                Voyager.arm.setMotor(0);
                Voyager.arm.enable();
                Voyager.arm.setSetpoint(Voyager.arm.getAngle());
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
