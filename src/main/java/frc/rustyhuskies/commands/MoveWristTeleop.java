package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.rustyhuskies.Voyager;

public class MoveWristTeleop extends Command {

    private long previousStickValue = 0;

    public MoveWristTeleop() {
        requires(Voyager.wrist);
    }

    @Override
    protected void execute() {
        long currentStickValue = Math.round(Voyager.oi.operatorStickLeft.getX());

        // Check to see if joystick has changed
        if (currentStickValue != previousStickValue) {
            if (currentStickValue != 0.) {
                // User is moving wrist, stop PID Controller
                Voyager.wrist.disable();
                Voyager.wrist.setMotor(currentStickValue);
            } else {
                // Stop wrist and enable PID Controller
                Voyager.wrist.setMotor(0.);
                Voyager.wrist.enable();
                Voyager.wrist.setSetpoint(Voyager.wrist.getAngle());
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
