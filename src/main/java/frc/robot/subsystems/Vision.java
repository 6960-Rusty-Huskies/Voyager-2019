package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.AutoSwitchCamera;
import frc.robot.utils.Axis;

/**
 * The cameras on the robot.
 */
public class Vision extends Subsystem {
  private VideoSink server;
  private UsbCamera frontCamera;
  private UsbCamera backCamera;

  public Vision() {
    frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.FRONT_CAMERA_PORT);
    backCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.BACK_CAMERA_PORT);
    server = CameraServer.getInstance().getServer();
  }

  public void setFrontCamera() {
    server.setSource(frontCamera); 
  }

  public void setBackCamera() {
    server.setSource(backCamera);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new AutoSwitchCamera(Robot.oi.driverStickLeft, Axis.x));
  }
}
