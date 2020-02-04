/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Shooter extends TraceableSubsystem {

  private CANSparkMax m_leftShooter;
  private CANSparkMax m_rightShooter;
  private SpeedControllerGroup m_shooter;
  private CANEncoder m_leftEncoder;
  private CANEncoder m_rightEncoder;
  private SpeedController m_kicker;
  private boolean isUpToSpeed;
  private XboxController controller;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Shooter(final ILogger logger) {
    super(logger);
    m_kicker = new PWMVictorSPX(0);
    m_leftShooter = new CANSparkMax(16, MotorType.kBrushless);
    m_leftShooter.setInverted(true);
    m_rightShooter = new CANSparkMax(17, MotorType.kBrushless);
    m_leftEncoder = m_leftShooter.getEncoder();
    m_rightEncoder = m_rightShooter.getEncoder();
    isUpToSpeed = false;
    controller = new XboxController(0);
    m_shooter = new SpeedControllerGroup(m_leftShooter, m_rightShooter);
  }

  @Override
  public void periodic() {
    super.periodic();
    // This method will be called once per scheduler run
  }

  public void shoot(Mode mode){
    


    if (controller.getAButton()){
      if (m_rightEncoder.getVelocity() > 5400){
        isUpToSpeed = true;
      } else{
        isUpToSpeed = false;
      }

      if (isUpToSpeed){
        if (controller.getBButton()){
          m_kicker.set(1);
        }
        m_shooter.set(0);
        
      }
      else{
        m_kicker.set(0);
        m_shooter.set(1);
        
      }
    
    } else if(controller.getXButton()){
      m_shooter.set(0.45);
      
      m_kicker.set(1);
    } else {
        if (m_rightEncoder.getVelocity() > 1000){
          m_shooter.set(-0.05);
        }else{
          m_shooter.set(0);
        }
      m_kicker.set(0);
      
    }
  
    
    
    SmartDashboard.putNumber("left Shooter RPM", m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right Shooter RPM", m_rightEncoder.getVelocity());
    SmartDashboard.putNumber("left Temp", m_leftShooter.getMotorTemperature());
    SmartDashboard.putNumber("right Temp", m_rightShooter.getMotorTemperature());


  }

  private static enum Mode{
    WARM_UP,
    SHOOT_LOW,
    SHOOT_HIGH,
    OFF;
  }
}