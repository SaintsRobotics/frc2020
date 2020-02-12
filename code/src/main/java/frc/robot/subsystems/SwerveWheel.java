
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.sql.Driver;

import com.fasterxml.jackson.core.sym.Name;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import frc.robot.common.AbsoluteEncoder;

public class SwerveWheel {
  private static final double kWheelRadius = 0.0508;
  private static final int kEncoderResolution = 4096;

  private static final double kModuleMaxAngularVelocity = Math.PI;
  private static final double kModuleMaxAngularAcceleration = 2 * Math.PI; // radians per second squared
  private static final double kTwoPi = 2 * Math.PI;

  private CANSparkMax m_driveMotor;
  private final SpeedController m_turningMotor;
  private final Translation2d m_location;
  private final CANEncoder m_driveEncoder;
  private final AbsoluteEncoder m_turningEncoder;
  private double PIDOutput;

  // private final PIDController m_drivePIDController = new PIDController(1, 0,
  // 0);

  private final PIDController m_turningPIDController = new PIDController(.3, 0, 0);

  public String m_name;

  private final double twoPI = kTwoPi;

  /**
   * Constructs a SwerveModule.
   *
   * @param driveMotor   drive motor.
   * @param turningMotor turning motor.
   * @param X            X displacement in meters from the pivot point of the
   *                     robot
   * @param Y            Y displacement in meters from the pivot point of the
   *                     robot
   */
  public SwerveWheel(CANSparkMax driveMotor, CANSparkMax turningMotor, double X, double Y, AbsoluteEncoder turnEncoder,
      String name) {
        PIDOutput = 0;
    m_name = name;
    m_driveMotor = driveMotor;
    m_turningMotor = turningMotor;
    m_driveEncoder = m_driveMotor.getEncoder();
    m_turningEncoder = turnEncoder;
    m_location = new Translation2d(X, Y);
    ((CANSparkMax) m_driveMotor).setSmartCurrentLimit(35, 60, 150);
    ((CANSparkMax) m_turningMotor).setSmartCurrentLimit(17, 30, 75);
    // Set the distance per pulse for the drive encoder. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.

    // Set the distance (in this case, angle) per pulse for the turning encoder.
    // This is the the angle through an entire rotation (2 * wpi::math::pi)
    // divided by the encoder resolution.
    // m_turningEncoder.setDistancePerPulse(kTwoPi / kEncoderResolution);

    // Limit the PID Controller's input range between -pi and pi and set the input
    // to be continuous.
    m_turningPIDController.enableContinuousInput(0, kTwoPi);

  }

  /**
   * @return the m_location
   */
  public Translation2d getlocation() {
    return m_location;
  }

  /**
   * Returns the current state of the module.
   *
   * @return The current state of the module.
   */
  public SwerveModuleState getState() {
    return new SwerveModuleState(m_driveEncoder.getVelocity(), new Rotation2d(m_turningEncoder.getRadians()));
  }

  /**
   * Sets the desired state for the module.
   *
   * @param state         Desired state with speed and angle.
   * @param maxWheelSpeed the maximum speed of a wheel in meters/second. use the
   *                      getMaxSpeed() method of the drivetrain
   */
  public void setDesiredState(SwerveModuleState state, double maxWheelSpeed) {
    // Calculate the drive output from the drive PID controller.
    var driveOutput = state.speedMetersPerSecond / maxWheelSpeed;

    // Calculate the turning motor output from the turning PID controller.
    driveOutput = smartInversion(state.angle.getRadians(), driveOutput);

    m_driveMotor.set(driveOutput);
    PIDOutput = m_turningPIDController.calculate(m_turningEncoder.getRadians());
    m_turningMotor.set(PIDOutput);

  }

  /**
   * Note: automatically sets the pid setpoint to the new targetHead; it doesn't
   * get returned
   * 
   * @param targetHead     heading value from swerve state
   * @param targetVelocity velocity value from swerve state
   * @return new target velocity. should be either the same or negative of the one
   *         given
   */
  public double smartInversion(double targetHead, double targetVelocity) {
    double currentHead = this.m_turningEncoder.getRadians();
    double diff = Math.abs(targetHead - currentHead);

    if (diff > Math.PI) {
      diff = kTwoPi - diff;
    }

    if (diff > Math.PI / 2) {
      targetHead += Math.PI;
      targetHead %= kTwoPi;
      targetVelocity *= -1;
    }

    // this.mpsToVoltOutput(targetVelocity);
    this.m_turningPIDController.setSetpoint(((targetHead % kTwoPi) + kTwoPi) % kTwoPi);
    return targetVelocity;
  }
    public double getPID(){
      return this.PIDOutput;
    }
}
