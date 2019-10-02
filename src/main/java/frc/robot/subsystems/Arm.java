package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArmTeleop;

/**
 * The arm connected to the frame of the robot that holds the wrist.
 */
public class Arm extends PIDSubsystem {

    // todo: these numbers all have to be verified... currently just a rough estimate
    // This is the range for the Arm where the wrist can't be fully extended without going out of bounds
    private static final double SAFE_ARM_ANGLE_LOW_FRONT = 60.0;
    private static final double SAFE_ARM_ANGLE_HIGH_FRONT = 120.0;
    private static final double SAFE_ARM_ANGLE_LOW_BACK = 240.0;
    private static final double SAFE_ARM_ANGLE_HIGH_BACK = 300.0;

    private SpeedController motor;
    private Encoder encoder;

    /**
     * The arm connected to the frame of the robot that holds the wrist.
     */
    public Arm() {
        super("Arm", 0.06, 0., 0.);

        encoder = new Encoder(RobotMap.ARM_ENCODER_A_CHANNEL, RobotMap.ARM_ENCODER_B_CHANNEL);
        encoder.setDistancePerPulse(360. / (RobotMap.ARM_ENCODER_PPR * RobotMap.ARM_GEAR_RATIO));
        encoder.setSamplesToAverage(7);
        encoder.setReverseDirection(true);

        Spark top = new Spark(RobotMap.ARM_MOTOR_TOP_CHANNEL);
        Spark bottom = new Spark(RobotMap.ARM_MOTOR_BOTTOM_CHANNEL);

        motor = new SpeedControllerGroup(top, bottom);
        motor.setInverted(true);

        setAbsoluteTolerance(0.5);
        setInputRange(0., 310.);
        setOutputRange(-0.6, 0.6);
        enable();
    }

    public void setMotor(double speed) {
        motor.set(speed * 0.5);
    }

    public void moveTo(double degrees) {
        setSetpoint(degrees);
    }

    public double getAngle() {
        return encoder.getDistance();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new MoveArmTeleop());
    }

    @Override
    public double returnPIDInput() {
        return getAngle();
    }

    @Override
    public void usePIDOutput(double output) {
        motor.pidWrite(output);
    }

    public boolean isWithinSafeZone(double currentPosition) {
        return (currentPosition < SAFE_ARM_ANGLE_LOW_FRONT)
                || (currentPosition > SAFE_ARM_ANGLE_HIGH_FRONT && currentPosition < SAFE_ARM_ANGLE_LOW_BACK)
                || (currentPosition > SAFE_ARM_ANGLE_HIGH_BACK);
    }

    /**
     * This assumes current position is in unsafe zone.
     *
     * @param currentPosition - current position of Arm.
     * @param goalPosition - where we want the Arm to go to.
     * @return whether or not the goal is outside of the current unsafe zone.
     */
    public boolean isLeavingUnsafeZone(double currentPosition, double goalPosition) {
        return (currentPosition < SAFE_ARM_ANGLE_LOW_FRONT && goalPosition >= SAFE_ARM_ANGLE_LOW_FRONT)
                || ((currentPosition > SAFE_ARM_ANGLE_HIGH_FRONT && currentPosition < SAFE_ARM_ANGLE_LOW_BACK)
                        && (goalPosition <= SAFE_ARM_ANGLE_HIGH_FRONT || goalPosition >= SAFE_ARM_ANGLE_LOW_BACK))
                || (currentPosition > SAFE_ARM_ANGLE_HIGH_BACK && goalPosition <= SAFE_ARM_ANGLE_HIGH_BACK);
    }

    /**
     * This assumes current position is in unsafe zone.
     *
     * @param currentPosition - current position of Arm.
     * @param goalPosition - where we want the Arm to go to.
     * @return nearest angle/position the Arm can move to that is on the way to the goal and is in safe zone.
     */
    public double nearestSafePositionToGoal(double currentPosition, double goalPosition) {
        // There are only 2 unsafe zones... just need to figure out if which one we are in and if we go up or down from there
        if (currentPosition < SAFE_ARM_ANGLE_HIGH_FRONT) {
            if (goalPosition > currentPosition) {
                return SAFE_ARM_ANGLE_HIGH_FRONT;
            } else {
                return SAFE_ARM_ANGLE_LOW_FRONT;
            }
        } else {
            if (goalPosition > currentPosition) {
                return SAFE_ARM_ANGLE_HIGH_BACK;
            } else {
                return SAFE_ARM_ANGLE_LOW_BACK;
            }
        }
    }

    /**
     * This assumes you are currently in a safe zone.
     *
     * @param currentPosition - current position of Arm.
     * @param goalPosition - where we want the Arm to go to.
     * @return whether or not the Arm is going to move through an unsafe zone based on the given goal position.
     */
    public boolean isMovingThroughUnsafeZone(double currentPosition, double goalPosition) {
        return (currentPosition < SAFE_ARM_ANGLE_LOW_FRONT && goalPosition >= SAFE_ARM_ANGLE_LOW_FRONT)
                || ((currentPosition > SAFE_ARM_ANGLE_HIGH_FRONT && currentPosition < SAFE_ARM_ANGLE_LOW_BACK)
                && (goalPosition <= SAFE_ARM_ANGLE_HIGH_FRONT || goalPosition >= SAFE_ARM_ANGLE_LOW_BACK))
                || (currentPosition > SAFE_ARM_ANGLE_HIGH_BACK && goalPosition <= SAFE_ARM_ANGLE_HIGH_BACK);
    }

}
