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
        
        public final double frontLeftOffset = 0.17;
        public final double frontRightOffset = 3.47;
        public final double rearLeftOffset = 3.50;
        public final double rearRightOffset = 2.50;

        public final double minSpeed = 0; //in m/s
        public final double maxSpeed = 1; //in m/s

        public final double PID_kProportional = maxSpeed/18;
        public final double PID_kIntegral = 0;
        public final double PID_kDerivative = 0;
        public final double PIDTolerance = 2.5;
                
        public final int driveCurrentStallLimit = 35; //in Amps
        public final int driveCurrentFreeLimit = 60; //in Amps
        public final int driveCurrentRPMLimit = 150; //in Amps

        public final int turnCurrentStallLimit = 17; //in Amps
        public final int turnCurrentFreeLimit = 30; //in Amps
        public final int turnCurrentRPMLimit = 75; //in Amps

   

        public final double swerveX = .335;
        public final double swerveY = .25;
    }

    public final class Physical {
        public final double widthInCms = 0;
        public final double lengthInCms = 0;
        public final double weightInKgs = 0;
        public static final double kWheelRadius = 0.0508;
        public static final int kEncoderResolution = 4096;
    }

    public final class Intake {
        public final int intakeControllerPort = 1;
        public final int armControllerPort = 2;
        public final int armEncoderPortA = 1;
        public final int armEncoderPortB = 1;

    }
}
