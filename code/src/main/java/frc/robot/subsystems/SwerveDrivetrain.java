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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.AbsoluteEncoder;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Position;
import frc.robot.common.TraceableSubsystem;

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
    private SpeedController m_frontLeftTurn;
    private SpeedController m_backRightTurn;
    private SpeedController m_backLeftTurn;
    private SpeedController m_frontRightTurn;
    private AbsoluteEncoder m_frontLeftEncoder;
    private AbsoluteEncoder m_frontRightEncoder;
    private AbsoluteEncoder m_backRightEncoder;
    private AbsoluteEncoder m_backLeftEncoder;
    private SwerveDriveKinematics m_kinematics;

    @Inject
    public SwerveDrivetrain(final ILogger logger, final RobotConfig config) {
        
        super(logger);
        _config = config;
        m_frontLeftDrive = new CANSparkMax(config.Drivetrain.frontLeftDriveMotorPort, MotorType.kBrushless);
    
        m_frontLeftTurn = new WPI_VictorSPX(config.Drivetrain.frontLeftTurnMotorPort);
        m_frontLeftEncoder = new AbsoluteEncoder(config.Drivetrain.frontLeftAbsoluteEncoder, 1.9,  false);
        ((CANSparkMax) m_frontLeftDrive).setSmartCurrentLimit(35, 60, 150);
        m_frontLeft = new SwerveWheel(m_frontLeftDrive, m_frontLeftTurn, config.Drivetrain.swerveX, config.Drivetrain.swerveY, m_frontLeftEncoder);


        m_frontRightDrive = new CANSparkMax(config.Drivetrain.frontRightDriveMotorPort, MotorType.kBrushless);        
        m_frontRightDrive.setInverted(true);
		((CANSparkMax) m_frontRightDrive).setSmartCurrentLimit(35, 60, 150);
        m_frontRightTurn = new WPI_VictorSPX(config.Drivetrain.frontRightTurnMotorPort);
        m_frontRightEncoder = new AbsoluteEncoder(config.Drivetrain.frontRightAbsoluteEncoder, 2.09+.1,false);
        m_frontRight = new SwerveWheel(m_frontRightDrive, m_frontRightTurn, config.Drivetrain.swerveX, -config.Drivetrain.swerveY, m_frontRightEncoder);



        m_backLeftDrive = new CANSparkMax(config.Drivetrain.rearLeftDriveMotorPort, MotorType.kBrushless);
        m_backLeftTurn = new WPI_VictorSPX(config.Drivetrain.rearLeftTurnMotorPort);
        m_backLeftEncoder = new AbsoluteEncoder(config.Drivetrain.rearLeftAbsoluteEncoder, 2.12, false);
        ((CANSparkMax) m_backLeftDrive).setSmartCurrentLimit(35,60,150);
        m_backLeft = new SwerveWheel(m_backLeftDrive, m_backLeftTurn, -config.Drivetrain.swerveX, config.Drivetrain.swerveY, m_backLeftEncoder);
        

        m_backRightDrive = new CANSparkMax(config.Drivetrain.rearRightDriveMotorPort, MotorType.kBrushless);
        m_backRightDrive.setInverted(true);
        m_backRightTurn = new WPI_VictorSPX(config.Drivetrain.rearRightturnMotorPort);
        m_backRightEncoder = new AbsoluteEncoder(config.Drivetrain.rearRightAbsoluteEncoder, 3.6, false);
        m_backRightDrive.setSmartCurrentLimit(35,60,150);
        m_backRight = new SwerveWheel(m_backRightDrive, m_backRightTurn, -config.Drivetrain.swerveX, -config.Drivetrain.swerveY, m_backRightEncoder);
        m_kinematics = new SwerveDriveKinematics(m_frontLeft.getlocation(), m_frontRight.getlocation(), m_backLeft.getlocation(), m_backRight.getlocation());
        
    }

    @Override
    public double getMinSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return 4.68490122217;
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

    @Override
    public void move(final double x, final double y, final double theta) {
        // TODO Auto-generated method stub

        this.getLogger().debug("x: " + x + ", y: " + y + ", theta: " + theta);
        var swerveModuleStates = m_kinematics.toSwerveModuleStates(new ChassisSpeeds(x, y, theta));
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, this.getMaxSpeed());
       //order of wheels in swerve module states is the same order as the wheels being inputed to Swerve kinematics
        m_frontLeft.setDesiredState(swerveModuleStates[0]);
        m_frontRight.setDesiredState(swerveModuleStates[1]);
        m_backLeft.setDesiredState(swerveModuleStates[2]);
        m_backRight.setDesiredState(swerveModuleStates[3]);
        // this.getLogger("frontLeft: ", m)
        SmartDashboard.putNumber("frontleft encoder ", m_frontLeftEncoder.getRadians());
        SmartDashboard.putNumber("frontright encoder ", m_frontRightEncoder.getRadians());
        SmartDashboard.putNumber("backleft encoder ", m_backLeftEncoder.getRadians());
        SmartDashboard.putNumber("backright encoder ", m_backRightEncoder.getRadians());
       
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
