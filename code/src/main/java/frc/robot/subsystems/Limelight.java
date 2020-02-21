/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
  /**
   * Getting limelight values
   */
  private NetworkTable m_limelight;

  public Limelight() {
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
    m_limelight.getEntry("pipeline").setNumber(0);      
  }

  public boolean ifTargetSeen(){
    return m_limelight.getEntry("tv").getDouble(0.0) == 1.0;
  }

  public void LEDState(int state){
    m_limelight.getEntry("ledMode").setNumber(state);
  }

  public int getPipeline(){
    return (int) m_limelight.getEntry("getpipe").getNumber(0);
  }

  public int getHeightAngularOffset(){
    return (int) m_limelight.getEntry("ty").getNumber(0);
  }

  public int getRotationalOffset(){
    return (int) m_limelight.getEntry("tx").getNumber(0);
  }

  public int getAreaOfScreenTakenByTheReflectiveTape(){
    return (int) m_limelight.getEntry("ta").getNumber(0);
  }
}
