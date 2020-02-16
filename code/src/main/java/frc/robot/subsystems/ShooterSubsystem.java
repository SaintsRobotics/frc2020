/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends TraceableMockSubsystem implements IShooterSubsystem {
    private boolean m_kickerEnabled;
    private boolean _isReady = false;
    private CANSparkMax m_leftShooter;
    private CANSparkMax m_rightShooter;
    private SpeedControllerGroup m_shooter;
    private double m_targetVelocity;
    private CANEncoder m_leftEncoder;
    private CANEncoder m_rightEncoder;
    private PIDController m_shooterPID;
    private RobotConfig _config;
    private boolean isUpToSpeed = false;

    private SpeedController m_kicker;

    @Inject
    public ShooterSubsystem(ILogger logger, final RobotConfig config) {


        super(logger);
        _config = config;
        m_leftShooter = new CANSparkMax(_config.Shooter.leftShooterPort, MotorType.kBrushless);
        m_rightShooter = new CANSparkMax(_config.Shooter.rightShooterPort, MotorType.kBrushless);
        m_leftShooter.setInverted(true);
        m_rightShooter.setSmartCurrentLimit(35, 60, 150);
        m_leftShooter.setSmartCurrentLimit(35, 60, 150);
        m_leftEncoder = m_leftShooter.getEncoder();
        m_rightEncoder = m_rightShooter.getEncoder();
        m_shooter = new SpeedControllerGroup(m_leftShooter, m_rightShooter);
        m_shooterPID = new PIDController(0.0003, 0.0004, 0);
        m_kicker = new WPI_VictorSPX(_config.Shooter.kickerPort);
        m_shooterPID.setTolerance(10);
        m_shooterPID.reset();

        // TODO Auto-generated constructor stub
    }

    /*
     * @param targetVelocity the rpm of shooter
     */
    @Override
    public void setSpeed(double targetVelocity) {
        m_shooterPID.setSetpoint(targetVelocity);
        m_targetVelocity = targetVelocity;
        m_shooterPID.reset();
    }

    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub

        return isUpToSpeed;
    }

    // @Override
    // public void enableFeeding(int direction) {
    //     // TODO Auto-generated method stub
    //     kickerEnabled = true;
    //     if (direction == 0){
    //         m_kicker.set(1);
    //     }
    //     else if (direction == 1){
    //         m_kicker.set(-1);
    //     }

    // }

    @Override
    public void feederForward() {
        m_kickerEnabled = true;
        m_kicker.set(1);
    }

    @Override
    public void feederBackward() {
        m_kickerEnabled = true;
        m_kicker.set(-1);
    }

    @Override
    public void disableFeeding() {
        // TODO Auto-generated method stub
        m_kickerEnabled = false;
        m_kicker.set(0);

    }

    @Override
    public void shootBalls(int balls) {
        // TODO Auto-generated method stub
    }

    @Override
    public void stopShooter() {

        setSpeed(0);

    }
    

    public void periodic() {
        isUpToSpeed = (Math.abs(m_leftEncoder.getVelocity() - m_targetVelocity) < 50 ) && (m_targetVelocity != 0);
        SmartDashboard.putNumber("Shooter Current RPM", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter Current RPM not graph", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter left Current", m_leftShooter.getBusVoltage());
        double shooterSpeed = m_shooterPID.calculate(m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter Pid Output", shooterSpeed);
        SmartDashboard.putBoolean("Is up to speed", isUpToSpeed);
        if (shooterSpeed > -0.2)
            m_shooter.set(shooterSpeed);
        else {
            m_shooter.set(-0.2);
        }
        
        if (m_targetVelocity == 0) {
            m_shooter.set(0);
        }
    }

}
