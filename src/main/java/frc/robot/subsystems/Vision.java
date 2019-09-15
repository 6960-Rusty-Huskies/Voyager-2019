package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.RobotMap;
import frc.robot.commands.AutoSwitchCamera;
import frc.robot.utils.Axis;

/**
 * The cameras on the robot.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VideoSink server;
  private UsbCamera frontCamera;
  private UsbCamera backCamera;
  private Accelerometer accelerometer;

  public Vision() {
    frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.FRONT_CAMERA_PORT);
    backCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.BACK_CAMERA_PORT);
    server = CameraServer.getInstance().getServer();
    accelerometer.setRange(Accelerometer.Range.k2G);
  }

  public void setFrontCamera() {
    server.setSource(frontCamera); 
  }

  public void setBackCamera() {
    server.setSource(backCamera);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new AutoSwitchCamera(Axis.x));
  }
}
