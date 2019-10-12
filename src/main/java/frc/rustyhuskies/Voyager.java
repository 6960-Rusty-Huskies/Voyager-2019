package frc.rustyhuskies;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.rustyhuskies.commands.MoveArmToLevel;
import frc.rustyhuskies.commands.ToggleClaw;
import frc.rustyhuskies.subsystems.*;
import frc.rustyhuskies.utils.Gamepiece;
import frc.rustyhuskies.utils.Level;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Voyager extends TimedRobot {

  public static DriveBase driveBase;
  public static Arm arm;
  public static Claw claw;
  public static Elevator elevator;
  public static Wrist wrist;
  public static OI oi;
  public static Vision vision;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    arm = new Arm();
    wrist = new Wrist();
    claw = new Claw();
    oi = new OI();
    vision = new Vision();
    driveBase = new DriveBase();
    elevator = new Elevator();
    oi = new OI();

    oi.clawToggleButton.whenReleased(new ToggleClaw());
    oi.elevatorToggleButton.whenReleased(new MoveArmToLevel(Level.bay, Gamepiece.cargo));

    oi.hatchLowButton.whenReleased(new MoveArmToLevel(Level.low, Gamepiece.hatch));
    oi.hatchMediumButton.whenReleased(new MoveArmToLevel(Level.middle, Gamepiece.hatch));
    oi.hatchHighButton.whenReleased(new MoveArmToLevel(Level.high, Gamepiece.hatch));
    oi.hatchGrabButton.whenReleased(new MoveArmToLevel(Level.grab, Gamepiece.hatch));

    oi.cargoLowButton.whenReleased(new MoveArmToLevel(Level.low, Gamepiece.cargo));
    oi.cargoMediumButton.whenReleased(new MoveArmToLevel(Level.middle, Gamepiece.cargo));
    oi.cargoHighButton.whenReleased(new MoveArmToLevel(Level.high, Gamepiece.cargo));
    oi.cargoGrabButton.whenReleased(new MoveArmToLevel(Level.grab, Gamepiece.cargo));
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putString("DB/String 0", "WE: " + Double.toString(wrist.getAngle()));
    SmartDashboard.putString("DB/String 1", "WS: " + Double.toString(wrist.getSetpoint()));
    SmartDashboard.putString("DB/String 2", "AE: " + Double.toString(arm.getAngle()));
    SmartDashboard.putString("DB/String 3", "AS: " + Double.toString(arm.getSetpoint()));
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called once when the robot enters Autonomous mode. Use it to 
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    arm.setSetpoint(arm.getAngle());
    wrist.setSetpoint(wrist.getAngle());
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
