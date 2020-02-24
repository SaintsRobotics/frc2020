/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.Inject;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;

public class Limelight {
  /**
   * Getting limelight values
   */
  private NetworkTable m_limelight;
  private RobotConfig m_config;

  @Inject
  public Limelight(RobotConfig config) {
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
    m_limelight.getEntry("pipeline").setNumber(0);
    m_config = config;
  }

  /**
   * @return if it can see the target
   */
  public boolean isTargetSeen() {
    return m_limelight.getEntry("tv").getDouble(0.0) == 1.0;
  }

  /**
   * @param state state 0 is the default value set in pipeline 1 is turning the
   *              LED's off 2 is forcing the LED's to blink 3 is turning the LED's
   *              on
   */
  public void setLEDState(int state) {
    m_limelight.getEntry("ledMode").setNumber(state);
  }

  /**
   * @return number of the current pipeline
   */
  public int getPipeline() {
    return (int) m_limelight.getEntry("getpipe").getNumber(0);
  }

  /**
   * @param int pipeline pipeline is the pipeline number you want to set it to
   */
  public void setPipeline(int pipeline) {
    m_limelight.getEntry("pipeline").setNumber(pipeline);
  }

  /**
   * @return the vertical angle offset from where the limelight is facing to the
   *         target
   */
  public double getHeightAngularOffset() {
    return m_limelight.getEntry("ty").getDouble(0);
  }

  /**
   * @return how far off the robot is turned from the
   */
  public double getRotationalOffset() {
    return m_limelight.getEntry("tx").getDouble(0);
  }

  /**
   * @return what percent of the limelight screen contains the target
   */
  public double getAreaOfScreenTakenByTarget() {
    return m_limelight.getEntry("ta").getDouble(0);
  }

  public double getDistanceFromTarget() {
    SmartDashboard.putNumber("targetHeightConfig", m_config.Limelight.targetHeightMeters);
    return (m_config.Limelight.targetHeightMeters - m_config.Limelight.mountingHeightMeters)
        / Math.tan(Math.toRadians(this.getHeightAngularOffset() + m_config.Limelight.mountingAngleDegrees));
  }
}