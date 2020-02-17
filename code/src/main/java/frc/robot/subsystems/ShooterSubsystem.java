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
    private CANSparkMax m_leftShooter;
    private CANSparkMax m_rightShooter;
    private SpeedControllerGroup m_shooter;
    private CANEncoder m_leftEncoder;
    private PIDController m_shooterPID;

    private boolean m_hasShotBall = true; // ONE ball, singular!

    private final int pidOnTargetTicks; // the number of ticks the pid must be on target for to be considered ready to
                                        // shoot
    private final int shooterCurrentThreshold; // if the current is less than this, that means we've shot a ball
    private int m_onTargetFor = 0; // the amount of ticks the pid has been on target

    private final int shooterRPM; //

    private SpeedController m_feeder;
    private boolean m_feedBackward = false;

    @Inject
    public ShooterSubsystem(ILogger logger, final RobotConfig config) {
        super(logger);
        m_leftShooter = new CANSparkMax(config.Shooter.leftShooterPort, MotorType.kBrushless);
        m_rightShooter = new CANSparkMax(config.Shooter.rightShooterPort, MotorType.kBrushless);
        m_leftShooter.setInverted(true);
        m_rightShooter.setSmartCurrentLimit(35, 60, 150);
        m_leftShooter.setSmartCurrentLimit(35, 60, 150);
        m_leftEncoder = m_leftShooter.getEncoder();
        m_shooter = new SpeedControllerGroup(m_leftShooter, m_rightShooter);
        m_shooterPID = new PIDController(0.0003, 0.0004, 0);
        m_feeder = new WPI_VictorSPX(config.Shooter.feederPort);
        m_shooterPID.setTolerance(50);
        m_shooterPID.reset();
        pidOnTargetTicks = config.Shooter.pidOnTargetTicks;
        shooterCurrentThreshold = config.Shooter.shooterCurrentThreshold;
        shooterRPM = config.Shooter.shooterRPM;
    }

    /*
     */
    @Override
    public void turnOnShooter() {
        m_shooterPID.reset();
        m_shooterPID.setSetpoint(shooterRPM);
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
        m_shooterPID.setSetpoint(0);
        this.m_hasShotBall = true;
    }

    public boolean getHasShotBall() {
        return m_hasShotBall;
    }

    private boolean isUpToSpeed() {
        if (m_shooterPID.atSetpoint() && m_shooterPID.getSetpoint() != 0) {
            m_onTargetFor++;
        } else {
            m_onTargetFor = 0;
        }

        return m_onTargetFor >= pidOnTargetTicks;
        // TODO whne should count be rest??

    }

    public void periodic() {
        double shooterSpeed = m_shooterPID.calculate(m_leftEncoder.getVelocity());
        if (shooterSpeed > -0.2)
            m_shooter.set(shooterSpeed);
        else {
            m_shooter.set(-0.2);
        }
        if (m_shooterPID.getSetpoint() == 0) {
            m_shooter.set(0);
        }

        if (m_feedBackward) {
            this.m_feeder.set(-1);
        } else if (!this.m_hasShotBall && this.isUpToSpeed()) {
            this.m_feeder.set(1);
        }
        if (m_leftShooter.getOutputCurrent() <= shooterCurrentThreshold) {
            this.m_hasShotBall = true;
            this.m_onTargetFor = 0;
        }

        SmartDashboard.putNumber("Shooter Pid Output", shooterSpeed);
        SmartDashboard.putNumber("Shooter RPM", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter left Current", m_leftShooter.getBusVoltage());
        SmartDashboard.putBoolean("has shot ball ", this.m_hasShotBall);
        SmartDashboard.putBoolean("m_feedBackward ", m_feedBackward);

    }

}
