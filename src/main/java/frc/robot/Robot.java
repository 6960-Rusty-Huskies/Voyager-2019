/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.GrabGamepiece;
import frc.robot.commands.MoveArmToLevel;
import frc.robot.commands.ToggleClaw;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Wrist;
import frc.robot.utils.Gamepiece;
import frc.robot.utils.Level;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, y ou must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveBase driveBase;
  public static Arm arm;
  public static Claw claw;
  public static Elevator elevator;
  public static Intake intake;
  public static Wrist wrist;
  public static OI oi;
  public static Vision vision;
  public static Compressor compressor;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    arm = new Arm();
    wrist = new Wrist();
    claw = new Claw();
    intake = new Intake();
    oi = new OI();
    vision = new Vision();
    compressor = new Compressor();
    compressor.stop();

    // elevator = new Elevator(RobotMap.ELEVATOR_LEFT_FORWARD_CHANNEL, RobotMap.ELEVATOR_LEFT_REVERSE_CHANNEL,
    //    RobotMap.ELEVATOR_RIGHT_FORWARD_CHANNEL, RobotMap.ELEVATOR_RIGHT_REVERSE_CHANNEL);
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
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    oi.clawToggleButton.whenPressed(new ToggleClaw());
    // oi.elevatorToggleButton.whenPressed(new ToggleElevator());

    oi.hatchLowButton.whenPressed(new MoveArmToLevel(Level.low, Gamepiece.hatch));
    oi.hatchMediumButton.whenPressed(new MoveArmToLevel(Level.medium, Gamepiece.hatch));
    oi.hatchHighButton.whenPressed(new MoveArmToLevel(Level.high, Gamepiece.hatch));
    oi.hatchGrabButton.whenPressed(new GrabGamepiece(Gamepiece.hatch));

    oi.cargoLowButton.whenPressed(new MoveArmToLevel(Level.low, Gamepiece.cargo));
    oi.cargoMediumButton.whenPressed(new MoveArmToLevel(Level.medium, Gamepiece.cargo));
    oi.cargoHighButton.whenPressed(new MoveArmToLevel(Level.high, Gamepiece.cargo));
    oi.cargoGrabButton.whenPressed(new GrabGamepiece(Gamepiece.cargo));
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
