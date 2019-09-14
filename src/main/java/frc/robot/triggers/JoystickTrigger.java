/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Triggers when a joystick is pushed past a deadzone.
 */
public class JoystickTrigger extends Trigger {
  private Joystick joystick;
  private double dZone;

  public JoystickTrigger(Joystick stick, double deadzone) {
    joystick = stick;
    dZone = deadzone;
  }

  @Override
  public boolean get() {
    return joystick.getMagnitude() > dZone;
  }
}
