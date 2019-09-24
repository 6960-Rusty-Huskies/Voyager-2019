package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick driverStickLeft = new Joystick(RobotMap.DRIVER_LEFT_JOYSTICK_PORT);
  public Joystick driverStickRight = new Joystick(RobotMap.DRIVER_RIGHT_JOYSTICK_PORT);

  public Joystick operatorStickLeft = new Joystick(RobotMap.OPERATOR_LEFT_JOYSTICK_PORT);
  public Joystick operatorStickRight = new Joystick(RobotMap.OPERATOR_RIGHT_JOYSTICK_PORT);

  public Button cargoLowButton = new JoystickButton(operatorStickRight, RobotMap.CARGO_LOW_PORT);
  public Button cargoMediumButton = new JoystickButton(operatorStickRight, RobotMap.CARGO_MEDIUM_PORT);
  public Button cargoHighButton = new JoystickButton(operatorStickRight, RobotMap.CARGO_HIGH_PORT);
  public Button cargoGrabButton = new JoystickButton(operatorStickRight, RobotMap.CARGO_GRAB_PORT);

  public Button hatchLowButton = new JoystickButton(operatorStickRight, RobotMap.HATCH_LOW_PORT);
  public Button hatchMediumButton = new JoystickButton(operatorStickRight, RobotMap.HATCH_MEDIUM_PORT);
  public Button hatchHighButton = new JoystickButton(operatorStickRight, RobotMap.HATCH_MEDIUM_PORT);
  public Button hatchGrabButton = new JoystickButton(operatorStickRight, RobotMap.HATCH_GRAB_PORT);

  public Button clawToggleButton = new JoystickButton(operatorStickRight, RobotMap.CLAW_TOGGLE_PORT);
  public Button elevatorToggleButton = new JoystickButton(operatorStickRight, RobotMap.ELEVATOR_TOGGLE_PORT);
}
