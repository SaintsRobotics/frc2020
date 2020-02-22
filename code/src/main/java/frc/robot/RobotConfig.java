/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;

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
    public final RobotConfig.Odometry Odometry = new RobotConfig.Odometry();
    public final RobotConfig.Limelight Limelight = new RobotConfig.Limelight();

    public final class Controller {
        public final int controllerPort = 0;

    }

    public final class Limelight {
        public final double kP = .05;
        public final double kI = 0.0;
        public final double kD = 0.0;
        public final double tolerance = 1;
        public final double setpoint = 0.0;
        public final double mountingAngle = 45.0;
        public final double mountingHeight = 0.508;
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
        public final int rearRightturnMotorPort = 7;
        public final int frontLeftAbsoluteEncoder = 0;
        public final int frontRightAbsoluteEncoder = 1;
        public final int rearLeftAbsoluteEncoder = 3;
        public final int rearRightAbsoluteEncoder = 2;
        public final double swerveX = .67 / 2;
        public final double swerveY = .25;
        public final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(new Translation2d(swerveX, swerveY),
                new Translation2d(swerveX, -swerveY), new Translation2d(-swerveX, swerveY),
                new Translation2d(-swerveX, -swerveY));
    }

    public final class Physical {
        public final double widthInCms = 0;
        public final double lengthInCms = 0;
        public final double weightInKgs = 0;
    }

    public final class Intake {
        public final int intakeControllerPort = 25;
        public final int armControllerPort = 24;

        public final double armLowerSetpoint = .305;
        public final double armInnerSetpoint = .115;

    }

    public final class Climber {
        public final int motorPort = 19;
    }

    public final class Shooter {
        public final int feederPort = 26;
        public final int spinnerPort = 27;
        public final int leftShooterPort = 16;
        public final int rightShooterPort = 17;

        public final int stallLimit = 30;
        public final int freeLimit = 60;
        public final int limitRPM = 150;

        public final double pidTolerance = 80;
        public final double pidP = 0.000129;
        public final double pidI = 0.0004;
        public final double pidD = 0;
        public final int pidOnTargetTicks = 10;

        public final int shooterRPM = 4900;
        public final double feederTimeoutSeconds = .5;

    }

    public final class Odometry {

        public final double pX = 1;
        public final double iX = 1;
        public final double dX = 1;
        public final double toleranceX = .02;

        public final double pY = 1;
        public final double iY = 1;
        public final double dY = 1;
        public final double toleranceY = .02;

        public final double pTheta = 1;
        public final double iTheta = 1;
        public final double dTheta = 1;
        public final double toleranceTheta = .02;

    }
}
