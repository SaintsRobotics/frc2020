/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
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
import frc.robot.common.Position;
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
    private SwerveDriveKinematics m_kinematics;
    private AHRS m_gyro;
    private PIDController m_pidController;

    private boolean m_isTurning;

    @Inject
    public SwerveDrivetrain(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;
        m_frontLeftDrive = new CANSparkMax(config.Drivetrain.frontLeftDriveMotorPort, MotorType.kBrushless);
        m_frontLeftDrive.setInverted(_config.Drivetrain.frontLeftDriveMotorInverted);
        m_frontLeftTurn = new CANSparkMax(config.Drivetrain.frontLeftTurnMotorPort, MotorType.kBrushless);
        m_frontLeftEncoder = new AbsoluteEncoder(config.Drivetrain.frontLeftAbsoluteEncoder, config.Drivetrain.frontLeftOffset, _config.Drivetrain.frontLeftEncoderInverted);

        m_frontLeft = new SwerveWheel(m_frontLeftDrive, m_frontLeftTurn, config.Drivetrain.swerveX,
                config.Drivetrain.swerveY, m_frontLeftEncoder, "Left front", _config);

        m_frontRightDrive = new CANSparkMax(config.Drivetrain.frontRightDriveMotorPort, MotorType.kBrushless);
        m_frontRightDrive.setInverted(_config.Drivetrain.frontRightDriveMotorInverted);
        m_frontRightTurn = new CANSparkMax(config.Drivetrain.frontRightTurnMotorPort, MotorType.kBrushless);
        m_frontRightEncoder = new AbsoluteEncoder(config.Drivetrain.frontRightAbsoluteEncoder, config.Drivetrain.frontRightOffset, _config.Drivetrain.frontRightEncoderInverted);

        m_frontRight = new SwerveWheel(m_frontRightDrive, m_frontRightTurn, config.Drivetrain.swerveX,
                -config.Drivetrain.swerveY, m_frontRightEncoder, "Right front", _config);

        m_backLeftDrive = new CANSparkMax(config.Drivetrain.rearLeftDriveMotorPort, MotorType.kBrushless);
        m_backLeftDrive.setInverted(_config.Drivetrain.rearLeftDriveMotorInverted);
        m_backLeftTurn = new CANSparkMax(config.Drivetrain.rearLeftTurnMotorPort, MotorType.kBrushless);
        m_backLeftEncoder = new AbsoluteEncoder(config.Drivetrain.rearLeftAbsoluteEncoder, config.Drivetrain.rearLeftOffset, _config.Drivetrain.rearLeftEncoderInverted);

        m_backLeft = new SwerveWheel(m_backLeftDrive, m_backLeftTurn, -config.Drivetrain.swerveX,
                config.Drivetrain.swerveY, m_backLeftEncoder, "Left back", _config);

        m_backRightDrive = new CANSparkMax(config.Drivetrain.rearRightDriveMotorPort, MotorType.kBrushless);
        m_backRightDrive.setInverted(_config.Drivetrain.rearRightDriveMotorInverted);
        m_backRightTurn = new CANSparkMax(config.Drivetrain.rearRightTurnMotorPort, MotorType.kBrushless);
        m_backRightEncoder = new AbsoluteEncoder(config.Drivetrain.rearRightAbsoluteEncoder, config.Drivetrain.rearRightOffset, _config.Drivetrain.rearRightEncoderInverted);

        m_backRight = new SwerveWheel(m_backRightDrive, m_backRightTurn, -config.Drivetrain.swerveX,
                -config.Drivetrain.swerveY, m_backRightEncoder, "Right back", _config);

        m_kinematics = new SwerveDriveKinematics(m_frontLeft.getlocation(), m_frontRight.getlocation(),
                m_backLeft.getlocation(), m_backRight.getlocation());

        m_pidController = new PIDController(_config.Drivetrain.PID_kDerivative, _config.Drivetrain.PID_kIntegral, _config.Drivetrain.PID_kProportional);
        m_pidController.enableContinuousInput(0, 360);
        m_pidController.setTolerance(config.Drivetrain.PIDTolerance);

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_gyro.reset();

        m_pidController.setSetpoint(m_gyro.getAngle());

    }

    @Override
    public double getMinSpeed() {
        // TODO Auto-generated method stub
        return _config.Drivetrain.minSpeed;
    }

    @Override
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return _config.Drivetrain.maxSpeed;
    }

    @Override
    public void moveForward(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveForward(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    /**
     * @param x     vertical speed, positive is forward in meters per second (max is
     *              3.66 m/s)
     * @param y     horizontal speed, postive is to the left in meters per second
     *              (max is 3.66 m/s)
     * @param theta rotational speed, positive is clockwise in radians per second
     *              (max unknown)
     */
    @Override
    public void move(double x, double y, double theta, final boolean fieldRelative) {
        // TODO Auto-generated method stub

        this.getLogger().debug("x: " + x + ", y: " + y + ", theta: " + theta);

        // Drag Heading Correction
        if (theta != 0.0) {
            m_isTurning = true;
        } else if (theta == 0.0 && this.m_isTurning) {
            this.m_pidController.setSetpoint((((this.m_gyro.getAngle() % 360) + 360) % 360));
            this.m_isTurning = false;
            theta = this.m_pidController.calculate((((this.m_gyro.getAngle() % 360) + 360) % 360));
        } else {
            theta = this.m_pidController.calculate((((this.m_gyro.getAngle() % 360) + 360) % 360));
        }

        SmartDashboard.putNumber("gyro angle ", ((this.m_gyro.getAngle() % (360)) + (360)) % (360));
        SmartDashboard.putNumber("heading pid calc ",
                this.m_pidController.calculate((((this.m_gyro.getAngle() % (360)) + (360)) % (360))));
        SmartDashboard.putNumber("heading pid error ", this.m_pidController.getPositionError());
        SmartDashboard.putBoolean("is turning ", this.m_isTurning);

        SwerveModuleState[] swerveModuleStates;
        if (fieldRelative) {
            swerveModuleStates = m_kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(x, y, theta,
                    new Rotation2d(2 * Math.PI - Math.toRadians(((m_gyro.getAngle() % 360) + 360) % 360))));
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

    }

    @Override
    public void moveLeft(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotate(final double degrees) {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceNorth() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceSouth() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceEast() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceWest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(final double finalHeading, final Position... waypoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(final double maxSpeed, final double finalHeading, final Position... waypoint) {
        // TODO Auto-generated method stub

    }
}
