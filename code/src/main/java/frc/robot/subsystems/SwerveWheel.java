/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.fasterxml.jackson.core.sym.Name;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogEncoder;
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

  private CANSparkMax m_driveMotor;
  private final SpeedController m_turningMotor;
  private final Translation2d m_location;
  private final CANEncoder m_driveEncoder;
  private final AbsoluteEncoder m_turningEncoder;

  private final PIDController m_drivePIDController = new PIDController(1, 0, 0);

  private final PIDController m_turningPIDController = new PIDController(.1, 0, 0);

  private String m_name;

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
    // m_turningEncoder.setDistancePerPulse(2 * Math.PI / kEncoderResolution);

    // Limit the PID Controller's input range between -pi and pi and set the input
    // to be continuous.
    m_turningPIDController.enableContinuousInput(0, 6);

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
   * @param state Desired state with speed and angle.
   */
  public void setDesiredState(SwerveModuleState state) {
    // Calculate the drive output from the drive PID controller.
    // TODO ds
    var driveOutput = state.speedMetersPerSecond / 3.63;// m_drivePIDController.calculate(m_driveEncoder.getVelocity()*0.0355,
                                                        // state.speedMetersPerSecond);
    SmartDashboard.putNumber(m_name + " target m/s", state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    var turnOutput = m_turningPIDController.calculate(m_turningEncoder.getRadians(),
        (state.angle.getRadians() % (2 * Math.PI) + (2 * Math.PI)) % (2 * Math.PI));
    // if (Math.abs(state.angle.getRadians() - m_turningEncoder.getRadians()) >
    // Math.PI) {
    // if ((turnOutput > 0 && state.angle.getRadians() -
    // m_turningEncoder.getRadians() > 0)
    // || (turnOutput < 0 && state.angle.getRadians() -
    // m_turningEncoder.getRadians() < 0)) {
    // turnOutput = m_turningPIDController.calculate(m_turningEncoder.getRadians(),
    // ((state.angle.getRadians() + Math.PI) % (2 * Math.PI) + (2 * Math.PI)) % (2 *
    // Math.PI));
    // driveOutput = 0 - driveOutput;
    // }
    // }

    SmartDashboard.putNumber(m_name + " Encoder Reading", m_turningEncoder.getRadians());
    SmartDashboard.putNumber(m_name + " Angle Target",
        (state.angle.getRadians() % (2 * Math.PI) + (2 * Math.PI)) % (2 * Math.PI));
    SmartDashboard.putNumber(m_name + " Turning PID output", m_turningPIDController.calculate(
        m_turningEncoder.getRadians(), (state.angle.getRadians() % (2 * Math.PI) + (2 * Math.PI)) % (2 * Math.PI)));
    // Calculate the turning motor output from the turning PID controller.
    m_driveMotor.set(driveOutput);
    m_turningMotor.set(turnOutput);
  }
}
