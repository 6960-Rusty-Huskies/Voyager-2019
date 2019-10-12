package frc.rustyhuskies.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.rustyhuskies.Voyager;
import frc.rustyhuskies.RobotMap;
import frc.rustyhuskies.commands.AutoSwitchCamera;
import frc.rustyhuskies.utils.Axis;

/**
 * The cameras on Voyager.
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
    setDefaultCommand(new AutoSwitchCamera(Voyager.oi.driverStickLeft, Axis.y));
  }
}
