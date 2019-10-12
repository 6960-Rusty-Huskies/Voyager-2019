package frc.rustyhuskies.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.rustyhuskies.Voyager;
import frc.rustyhuskies.utils.Gamepiece;
import frc.rustyhuskies.utils.Level;

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
                        armAngleGoal = 0.;
                        wristAngleGoal = 0.;
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
            case grab:
                switch(gamepiece) {
                    case cargo:
                        armAngleGoal = 81.;
                        wristAngleGoal = 78.;
                        break;
                    case hatch:
                        armAngleGoal = 40.;
                        wristAngleGoal = 90;
                }
            break;
            case bay:
                switch(gamepiece) {
                    case cargo:
                        armAngleGoal = 135.;
                        wristAngleGoal = 270.;
                        break;
                    case hatch:
                        armAngleGoal = Voyager.arm.getAngle();
                        wristAngleGoal = Voyager.wrist.getAngle();
                }
            default:
                armAngleGoal = Voyager.arm.getAngle();
                wristAngleGoal = Voyager.wrist.getAngle();
        }

        addSequential(new MoveWristToAngle(0.));
        addSequential(new MoveArmToAngle(armAngleGoal));
        addSequential(new MoveWristToAngle(wristAngleGoal));
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || Voyager.oi.operatorStickLeft.getMagnitude() > 0.1;
    }
}
