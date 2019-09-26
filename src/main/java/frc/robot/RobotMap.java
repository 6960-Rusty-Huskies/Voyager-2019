package frc.robot;

/**
 * A mapping from the ports that sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // PWM
  public static final int DRIVE_RIGHT_TOP_CHANNEL = 0;
  public static final int DRIVE_RIGHT_FRONT_CHANNEL = 1;
  public static final int DRIVE_RIGHT_BACK_CHANNEL = 2;

  public static final int DRIVE_LEFT_TOP_CHANNEL = 3;
  public static final int DRIVE_LEFT_FRONT_CHANNEL = 4;
  public static final int DRIVE_LEFT_BACK_CHANNEL = 5;

  public static final int ARM_MOTOR_TOP_CHANNEL = 6;
  public static final int ARM_MOTOR_BOTTOM_CHANNEL = 7;

  public static final int INTAKE_BASE_PORT_CHANNEL = 8;
  public static final int INTAKE_ROLLER_PORT_CHANNEL = 9;

  // DIO
  public static final int ARM_ENCODER_A_CHANNEL = 0;
  public static final int ARM_ENCODER_B_CHANNEL = 1;

  // CAN
  public static final int WRIST_CAN_ID = 1;

  public static final int CLAW_FORWARD_CHANNEL = 0;
  public static final int CLAW_REVERSE_CHANNEL = 1;

  /** The port for the left elevator solenoid to move to DoubleSolenoid.Value.kForward. */
  public static final int ELEVATOR_LEFT_FORWARD_CHANNEL = 0;
  /** The port for the left elevator solenoid to move to DoubleSolenoid.Value.kReverse. */
  public static final int ELEVATOR_LEFT_REVERSE_CHANNEL = 0;
  /** The port for the right elevator solenoid to move to DoubleSolenoid.Value.kForward. */
  public static final int ELEVATOR_RIGHT_FORWARD_CHANNEL = 0;
  /** The port for the right elevator solenoid to move to DoubleSolenoid.Value.kReverse. */
  public static final int ELEVATOR_RIGHT_REVERSE_CHANNEL = 0;

  // USB
  public static final int DRIVER_LEFT_JOYSTICK_PORT = 0;
  public static final int DRIVER_RIGHT_JOYSTICK_PORT = 1;

  public static final int OPERATOR_LEFT_JOYSTICK_PORT = 3;
  public static final int OPERATOR_RIGHT_JOYSTICK_PORT = 2;

  public static final int CARGO_LOW_PORT = 8;
  public static final int CARGO_MEDIUM_PORT = 7;
  public static final int CARGO_HIGH_PORT = 6;
  public static final int CARGO_GRAB_PORT = 5;

  public static final int HATCH_LOW_PORT = 4;
  public static final int HATCH_MEDIUM_PORT = 3;
  public static final int HATCH_HIGH_PORT = 2;
  public static final int HATCH_GRAB_PORT = 1;

  public static final int CLAW_TOGGLE_PORT = 9;
  public static final int ELEVATOR_TOGGLE_PORT = 10;

  public static final int FRONT_CAMERA_PORT = 0;
  public static final int BACK_CAMERA_PORT = 1;

  // Physical measurements

  /** The height of the arm, from the bottom of the wheel to its pivot point, in inches. */
  public static final double ARM_HEIGHT = 42.0;

  /** The radius of the arm from the pivot point to the other end, in inches. */
  public static final double ARM_RADIUS = 32.0;

  /** The gear ratio of the arm encoder. Used to determine the distance per pulse of the arm. */
  public static final double ARM_GEAR_RATIO = 1 / 1;
  /** The gear ratio of the wrist encoder. Used in the setDistancePerPulse() function. */
  public static final double WRIST_GEAR_RATIO = 45 / 1;

  // Misc. constants

  /** The number of pulses sent by the arm encoder per full revolution. */
  public static final double ARM_ENCODER_PPR = 600.0;
}
