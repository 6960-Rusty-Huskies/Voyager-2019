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

        addSequential(new MoveWristToAngle(0.));
        addSequential(new MoveArmToAngle(armAngleGoal));
        addSequential(new MoveWristToAngle(wristAngleGoal));
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || Robot.oi.operatorStickLeft.getMagnitude() > 0.1;
    }
}
