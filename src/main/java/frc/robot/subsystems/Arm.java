package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArmTeleop;

/**
 * The arm connected to the frame of the robot that holds the wrist.
 */
public class Arm extends PIDSubsystem {
    private Spark motor;
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

        motor = new Spark(RobotMap.ARM_MOTOR_CHANNEL);
        motor.setInverted(true);

        setAbsoluteTolerance(0.5);
        setInputRange(0., 310.);
        setOutputRange(-0.7, 0.7);
        enable();
    }

    public void setMotor(double speed) {
        motor.set(speed * 0.6);
    }

    public void moveTo(double degrees) {
        setSetpoint(degrees);
    }

    public double getAngle() {
        return encoder.getDistance();
    }

    public double getXComponent() {
        return RobotMap.ARM_RADIUS * Math.sin(Math.toRadians(getAngle()));
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
}
