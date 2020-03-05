/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.google.inject.Inject;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.AbsoluteEncoder;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class SwerveDrivetrain extends TraceableSubsystem implements IDrivetrainSubsystem {

        private RobotConfig _config;
        private SwerveWheel m_frontLeft;
        private SwerveWheel m_backRight;
        private SwerveWheel m_backLeft;
        private SwerveWheel m_frontRight;
        private CANSparkMax m_frontLeftDrive;
        private CANSparkMax m_backRightDrive;
        private CANSparkMax m_backLeftDrive;
        private CANSparkMax m_frontRightDrive;
        private CANSparkMax m_frontLeftTurn;
        private CANSparkMax m_backRightTurn;
        private CANSparkMax m_backLeftTurn;
        private CANSparkMax m_frontRightTurn;
        public AbsoluteEncoder m_frontLeftEncoder;
        public AbsoluteEncoder m_frontRightEncoder;
        public AbsoluteEncoder m_backRightEncoder;
        public AbsoluteEncoder m_backLeftEncoder;
        public CANEncoder m_leftdrive;
        public CANEncoder m_leftturn;
        private SwerveDriveKinematics m_kinematics;
        private AHRS m_gyro;
        private PIDController m_pidController;

        private boolean m_isTurning;

        @Inject
        public SwerveDrivetrain(final ILogger logger, final RobotConfig config) {
                super(logger);
                double widthOffset = config.Physical.widthInMeters / 2;
                double lengthOffset = config.Physical.lengthInMeters / 2;

                _config = config;
                m_frontLeftDrive = new CANSparkMax(config.Drivetrain.frontLeftDriveMotorPort, MotorType.kBrushless);
                m_frontLeftDrive.setInverted(true);
                m_frontLeftTurn = new CANSparkMax(config.Drivetrain.frontLeftTurnMotorPort, MotorType.kBrushless);
                m_frontLeftEncoder = new AbsoluteEncoder(config.Drivetrain.frontLeftAbsoluteEncoder, 2.254991, true);

                m_frontLeft = new SwerveWheel(m_frontLeftDrive, m_frontLeftTurn, widthOffset, lengthOffset,
                                m_frontLeftEncoder, "Left front");

                m_frontRightDrive = new CANSparkMax(config.Drivetrain.frontRightDriveMotorPort, MotorType.kBrushless);
                m_frontRightDrive.setInverted(false);
                m_frontRightTurn = new CANSparkMax(config.Drivetrain.frontRightTurnMotorPort, MotorType.kBrushless);
                m_frontRightEncoder = new AbsoluteEncoder(config.Drivetrain.frontRightAbsoluteEncoder, 2.466641, true);

                m_frontRight = new SwerveWheel(m_frontRightDrive, m_frontRightTurn, widthOffset, -lengthOffset,
                                m_frontRightEncoder, "Right front");

                m_backLeftDrive = new CANSparkMax(config.Drivetrain.rearLeftDriveMotorPort, MotorType.kBrushless);
                m_backLeftDrive.setInverted(true);
                m_backLeftTurn = new CANSparkMax(config.Drivetrain.rearLeftTurnMotorPort, MotorType.kBrushless);
                m_backLeftEncoder = new AbsoluteEncoder(config.Drivetrain.rearLeftAbsoluteEncoder, .279, true);
                m_backLeft = new SwerveWheel(m_backLeftDrive, m_backLeftTurn, -widthOffset, lengthOffset,
                                m_backLeftEncoder, "Left back");

                m_backRightDrive = new CANSparkMax(config.Drivetrain.rearRightDriveMotorPort, MotorType.kBrushless);
                m_backRightDrive.setInverted(false);
                m_backRightTurn = new CANSparkMax(config.Drivetrain.rearRightturnMotorPort, MotorType.kBrushless);
                m_backRightEncoder = new AbsoluteEncoder(config.Drivetrain.rearRightAbsoluteEncoder, 3.925, true);
                m_backRight = new SwerveWheel(m_backRightDrive, m_backRightTurn, -widthOffset, -lengthOffset,
                                m_backRightEncoder, "Right back");
                m_kinematics = new SwerveDriveKinematics(m_frontLeft.getlocation(), m_frontRight.getlocation(),
                                m_backLeft.getlocation(), m_backRight.getlocation());

                m_pidController = new PIDController((getMaxSpeed() / 180) * 5, 0, 0);

                m_pidController.enableContinuousInput(0, 360);
                m_pidController.setTolerance(10);

                m_gyro = new AHRS(SPI.Port.kMXP);

                m_leftdrive = m_frontLeftDrive.getEncoder();
                m_leftturn = m_frontLeftTurn.getEncoder();
        }

        public void resetGyro() {
                this.m_gyro.reset();
                this.m_pidController.setSetpoint(0);
                DriverStation.reportError("gyro reset ", false);
        }

        @Override
        public double getMinSpeed() {
                // TODO Auto-generated method stub
                return 0;
        }

        @Override
        public double getMaxSpeed() {
                return _config.Drivetrain.maxMetersPerSecond;
        }

        @Override
        public double getMaxAngularSpeed() {
                return _config.Drivetrain.maxRadiansPerSecond;
        }

        @Override
        public void move(double x, double y, double theta, final boolean fieldRelative) {

                this.getLogger().debug("x: " + x + ", y: " + y + ", theta: " + theta);

                // Drag Heading Correction
                if (theta != 0.0) {
                        m_isTurning = true;

                        // Sets the setpoint to maintain the heading the tick after you release the
                        // joystick

                } else if (theta == 0.0 && this.m_isTurning) {
                        this.m_pidController.setSetpoint((((this.m_gyro.getAngle() % 360) + 360) % 360));
                        this.m_isTurning = false;
                        theta = this.m_pidController.calculate((((this.m_gyro.getAngle() % 360) + 360) % 360));

                }
                // applies heading correction only when moving and not sending a input command
                else if (x != 0 && y != 0) {
                        theta = this.m_pidController.calculate((((this.m_gyro.getAngle() % 360) + 360) % 360));
                }

                // SmartDashboard.putNumber("x", x);
                // SmartDashboard.putNumber("y", y);
                // SmartDashboard.putNumber("theta", theta);

                SwerveModuleState[] swerveModuleStates;

                // Field relative conversion
                if (fieldRelative) {
                        swerveModuleStates = m_kinematics.toSwerveModuleStates(
                                        ChassisSpeeds.fromFieldRelativeSpeeds(x, y, theta, new Rotation2d(2 * Math.PI
                                                        - Math.toRadians(((m_gyro.getAngle() % 360) + 360) % 360))));
                } else {
                        swerveModuleStates = m_kinematics.toSwerveModuleStates(new ChassisSpeeds(x, y, theta));
                }
                SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, this.getMaxSpeed());
                // order of wheels in swerve module states is the same order as the wheels being
                // inputed to Swerve kinematics
                m_frontLeft.setDesiredState(swerveModuleStates[0], this.getMaxSpeed());
                m_frontRight.setDesiredState(swerveModuleStates[1], this.getMaxSpeed());
                m_backLeft.setDesiredState(swerveModuleStates[2], this.getMaxSpeed());
                m_backRight.setDesiredState(swerveModuleStates[3], this.getMaxSpeed());
                // this.getLogger("frontLeft: ", m)
                SmartDashboard.putNumber("subsystem m/s x", x);
                SmartDashboard.putNumber("subsystem m/s y", y);
                SmartDashboard.putNumber("subsystem m/s r", theta);
                // System.out.println("x: " + x + "y:" + y + "Theta: " + theta);

        }

        @Override
        public void setToBrakeMode() {
                m_frontLeftDrive.setIdleMode(IdleMode.kBrake);
                m_frontRightDrive.setIdleMode(IdleMode.kBrake);
                m_backLeftDrive.setIdleMode(IdleMode.kBrake);
                m_backRightDrive.setIdleMode(IdleMode.kBrake);
        }

        @Override
        public void setToCoastMode() {
                m_frontLeftDrive.setIdleMode(IdleMode.kCoast);
                m_frontRightDrive.setIdleMode(IdleMode.kCoast);
                m_backLeftDrive.setIdleMode(IdleMode.kCoast);
                m_backRightDrive.setIdleMode(IdleMode.kCoast);
        }

        public void periodic() { // Debugging output mostly
                // SmartDashboard.putNumber("back left ", m_backLeftEncoder.getRadians());
                // SmartDashboard.putNumber("back right ", m_backRightEncoder.getRadians());
                // SmartDashboard.putNumber("front left ", m_frontLeftEncoder.getRadians());
                // SmartDashboard.putNumber("front right ", m_frontRightEncoder.getRadians());
                SmartDashboard.putNumber("Gyro VAlue", ((m_gyro.getAngle() % 360) + 360) % 360);
                // SmartDashboard.putNumber("gyro angle fed to field relative ",
                // (360 - (this.m_gyro.getAngle() % (360)) + (360)) % (360));

                // SmartDashboard.putNumber("heading pid error ",
                // this.m_pidController.getPositionError());
                // SmartDashboard.putBoolean("is turning ", this.m_isTurning);
                // SmartDashboard.putNumber("CAN LEFT DRIVE power", m_leftdrive.getVelocity());
        }

        public double getGyroAngle() {
                return m_gyro.getAngle();
        }
}
