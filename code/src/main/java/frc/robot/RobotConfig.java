/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Defines all the configuration for your Robot. This class is not static to
 * allow it to be injected wherever its needed. Statics reduce the ability to
 * unit test code and should be avoided if at all possible.
 */
public final class RobotConfig {
    public final RobotConfig.Controller Controller = new RobotConfig.Controller();
    public final RobotConfig.SwerveDrivetrain Drivetrain = new RobotConfig.SwerveDrivetrain();
    public final RobotConfig.Physical Physical = new RobotConfig.Physical();
    public final RobotConfig.Intake Intake = new RobotConfig.Intake();
    public final RobotConfig.Shooter Shooter = new RobotConfig.Shooter();
    public final RobotConfig.SwerveWheel Wheel = new RobotConfig.SwerveWheel();



    public final class Controller {
        public final int driverPort = 0;
        public final int operatorPort = 1;
        public final double leftXdeadzone = 0.1;
        public final double leftYdeadzone = 0.1;
        public final double rightXdeadzone = 0.1;
    }

    public final class SwerveDrivetrain {
        // examples to be filled out in detail during drivetrain development
        public final int frontLeftDriveMotorPort = 8;
        public final int frontLeftTurnMotorPort = 1;
        public final int rearLeftDriveMotorPort = 2;
        public final int rearLeftTurnMotorPort = 3;
        public final int frontRightDriveMotorPort = 4;
        public final int frontRightTurnMotorPort = 5;
        public final int rearRightDriveMotorPort = 6;
        public final int rearRightTurnMotorPort = 7;

        public final int frontLeftAbsoluteEncoder = 0;
        public final int frontRightAbsoluteEncoder = 1;
        public final int rearLeftAbsoluteEncoder = 3;
        public final int rearRightAbsoluteEncoder = 2;
        
        public final double frontLeftOffset = 5.664991;
        public final double frontRightOffset = 2.466641;
        public final double rearLeftOffset = .279;
        public final double rearRightOffset = 3.925;

        public final boolean frontLeftDriveMotorInverted = true;
        public final boolean frontLeftEncoderInverted = true;
        public final boolean frontRightDriveMotorInverted = false;
        public final boolean frontRightEncoderInverted = true;
        public final boolean rearLeftDriveMotorInverted = true;
        public final boolean rearLeftEncoderInverted = true;
        public final boolean rearRightDriveMotorInverted = false;
        public final boolean rearRightEncoderInverted = true;


        public final double minSpeed = 0; //in m/s
        public final double maxSpeed = 0.5; //in m/s

        public final double PID_kProportional = maxSpeed/18;
        public final double PID_kIntegral = 0;
        public final double PID_kDerivative = 0;
        public final double PIDTolerance = 10;
                
        public final int driveCurrentStallLimit = 35; //in Amps
        public final int driveCurrentFreeLimit = 60; //in Amps
        public final int driveCurrentRPMLimit = 150; //in Amps

        public final int turnCurrentStallLimit = 17; //in Amps
        public final int turnCurrentFreeLimit = 30; //in Amps
        public final int turnCurrentRPMLimit = 75; //in Amps

        public final int STATIC_DRIVE_COEFFICIENT = 1;
        public final int STATIC_TURN_COEFFICIENT = 1;
        
        public final double swerveX = .335;
        public final double swerveY = .25;
    }

    public final class SwerveWheel{
        public final double turning_kP = 0.1;
        public final double turning_kI = 0;
        public final double turning_kD = 0;

    }

    public final class Physical {
        public final double widthInCms = 0;
        public final double lengthInCms = 0;
        public final double weightInKgs = 0;
        public static final double kWheelRadius = 0.0508;
        public static final int kEncoderResolution = 4096;
    }

    public final class Intake {
        public final int intakeControllerPort = 25;
        public final int armControllerPort = 24;
        public final int armEncoderPort = 7;

        public final double armTolerance = 0.005;
        public final double armLowerSetpoint = .305;
        public final double armInnerSetpoint = .115;
        public final boolean armInverted = true;
        public final boolean intakeInverted = true;

        public final double isLoweredLowThreshhold = 0.3;
        public final double isLoweredHighThreshhold = 0.32;
        public final double intakeForwardSpeed = 0.6;
        public final double intakeReverseSpeed = -0.6;

    }

    public final class Climber {
        public final int motorPort = 19;
    }

    public final class Shooter {

        public final int kickerPort = 26;
        public final int leftShooterPort = 16;
        public final int rightShooterPort = 17;

        public final int speed = 4900; //in rpm

        public final int shooterCurrentStallLimit = 35; //in Amps
        public final int shooterCurrentFreeLimit = 60; //in Amps
        public final int shooterCurrentRPMLimit = 150;  //in Amps

        public final double PID_kProportional = 0.0003;
        public final double PID_kIntegral = 0.0004;
        public final double PID_kDerivative = 0;
        public final double PIDTolerance = 10;

        public final int speedTolerance = 50;
        public final double bangbangTolerance = -0.2;
    }

}
