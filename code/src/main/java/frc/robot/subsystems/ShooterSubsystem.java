/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;
import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableSubsystem;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends TraceableSubsystem implements IShooterSubsystem {
    private CANSparkMax m_leftShooter;
    private CANSparkMax m_rightShooter;
    private SpeedControllerGroup m_shooter;
    private CANEncoder m_leftEncoder;
    private RobotConfig _config;
    private boolean m_hasShotBall = true; // ONE ball, singular!
    private boolean m_isShooting = false;

    private double m_targetShooterSpeed;
    private SpeedController m_wheels;
    private SpeedController m_kicker;
    private SpeedControllerGroup m_feeder;
    private boolean m_feedBackward = false;
    private boolean m_isShooterOn = false;

    // private TimeOfFlight m_beambreak;

    @Inject
    public ShooterSubsystem(ILogger logger, final RobotConfig config) {
        super(logger);
        _config = config;
        m_leftShooter = new CANSparkMax(config.Shooter.leftShooterPort, MotorType.kBrushless);
        m_rightShooter = new CANSparkMax(config.Shooter.rightShooterPort, MotorType.kBrushless);
        m_leftShooter.setInverted(true);
        m_rightShooter.setSmartCurrentLimit(config.Shooter.stallLimit, config.Shooter.freeLimit,
                config.Shooter.limitRPM);
        m_leftShooter.setSmartCurrentLimit(config.Shooter.stallLimit, config.Shooter.freeLimit,
                config.Shooter.limitRPM);
        m_leftEncoder = m_leftShooter.getEncoder();
        m_shooter = new SpeedControllerGroup(m_leftShooter, m_rightShooter);
        this.m_targetShooterSpeed = config.Shooter.shooterDefaultRPM;

        m_kicker = new WPI_VictorSPX(config.Shooter.feederPort);
        m_wheels = new WPI_VictorSPX(config.Shooter.spinnerPort);
        m_wheels.setInverted(true);

        m_feeder = new SpeedControllerGroup(m_kicker, m_wheels);

        // m_beambreak = new TimeOfFlight(_config.Shooter.beamBrakeSensorPort);

    }

    /*
     */
    @Override
    public void turnOnShooter(double speed) {
        m_isShooterOn = true;
        this.m_targetShooterSpeed = speed;
    }

    /**
     * basically, this makes sure one ball is yeeted, if feedbackward is false. if
     * feedbackward is true, it just drives the feeder in the opposite direction,
     * probably becasue mech didn't fix their problems, and the balls got jammed
     */
    @Override
    public void feed(boolean feedBackward) {
        this.m_hasShotBall = false;
        this.m_feedBackward = feedBackward;
    }

    @Override
    public void stopFeeding() {
        this.m_hasShotBall = true;
    }

    @Override
    public void turnOffShooter() {
        m_isShooterOn = false;
        this.m_hasShotBall = true;
    }

    public boolean getHasShotBall() {
        return m_hasShotBall;
    }

    private boolean hasBeamBroken() {
        return false;
        // return m_beambreak.getRange() <= _config.Shooter.beamBrakeLimit;
    }

    public void periodic() {

        if (m_isShooterOn) {
            m_shooter.set(this.m_targetShooterSpeed);
        } else {
            m_shooter.set(0);
        }

        // not having shot a ball implies that a button is being pressed, and we want
        // the feeder to be driven
        if (!this.m_hasShotBall) {
            if (this.m_feedBackward) {
                this.m_feeder.set(-1);

            } else {
                this.m_feeder.set(1);
                this.m_isShooting = true;
            }
        }

        // if we are/were shooting and the pid isn't at the setpoint, that means we've
        // shot a ball
        if (this.m_isShooting && this.hasBeamBroken()) {// && this.hasBeamBroken()) {
            this.m_hasShotBall = true;
            this.m_isShooting = false;
        }

        // m_hasShotBall is used to abort the feeder, spinning forward or backward
        if (this.m_hasShotBall) {
            this.m_feeder.set(0);
        }

        SmartDashboard.putNumber("Shooter RPM", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("rpm error ", m_targetShooterSpeed - m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter Current", m_leftShooter.getBusVoltage());
        SmartDashboard.putNumber("shooter motor input ", m_leftShooter.get());
        SmartDashboard.putNumber("target shooter rpm ", m_targetShooterSpeed);
        SmartDashboard.putBoolean("beam has broken ", this.hasBeamBroken());
        // SmartDashboard.putNumber("playing with fusion", m_beambreak.getRange());

    }

}
