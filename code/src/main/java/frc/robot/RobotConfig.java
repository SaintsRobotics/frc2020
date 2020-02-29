/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

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
    public final RobotConfig.Limelight Limelight = new RobotConfig.Limelight();
    public final RobotConfig.Climber Climber = new RobotConfig.Climber();
    public final RobotConfig.TurnToHeading turnToHeading = new RobotConfig.TurnToHeading();

    public final class Controller {
        public final int driverControllerPort = 0;
        public final int operatorControllerPort = 1;

        public final int resetGyroButtonPort = XboxController.Button.kStart.value;
        public final int driveMotorIdleStateButtonPort = XboxController.Button.kBumperLeft.value;

        public final int shooterStartupButtonPort = XboxController.Button.kA.value;
        public final int feedBackwardButtonPort = XboxController.Button.kB.value;
        public final int feedOneBallButtonPort = XboxController.Button.kX.value;
        public final int shooterShutdownButtonPort = XboxController.Button.kY.value;
        public final int intakeInButtonPort = XboxController.Button.kBumperRight.value;
        public final int intakeOutButtonPort = XboxController.Button.kBumperLeft.value;

        public final int visionTrackButtonPort = XboxController.Button.kB.value;

        public final int fieldWestButtonAngle = 270;
        public final int fieldEastButtonAngle = 90;
        public final int fieldNorthButtonAngle = 0;
        public final int fieldSouthButtonAngle = 270;

        public final double kDriveDeadzone = 0.2;
        public final double kDriveScale = .75;
        public final double kTurnDeadzone = 0.2;
        public final double kTurnScale = .2;
        public final int climberReleaseButtonPort = XboxController.Button.kBack.value;
    }

    public final class SwerveDrivetrain {
        public final double maxMetersPerSecond = 3.66;
        public final double maxRadiansPerSecond = 8.76;
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

    }

    public final class Physical {
        /*
         * When using these values for the swerve drive, make sure to divide them by two
         * if the pivot point of the bot is in the center.
         * 
         * Note that standard convention is the bot is centered on the origin, facing
         * right, along the positive x-axis. So the top-left portion of the bot is in
         * the first quadrant, the back-left is in the second quadrant, the back-right
         * is in the third quadrant, and the back-right is in the fourth quadrant.
         */

        // Note: these are the distances of the swerve wheels to each other, not the
        // size of the bot's frame.
        public final double widthInMeters = .67;
        public final double lengthInMeters = .5;
        public final double weightInKgs = 0; // TODO this isn't the real weight!!!
    }

    public final class Intake {
        public final int intakeControllerPort = 25;
        public final int armControllerPort = 24;

        public final double armLowerSetpoint = .305;
        public final double armInnerSetpoint = .115;

    }

    public final class Climber {
        public final int winchPort = 19;
        public final int servoPort = 1;
        public final double servoReleasePosition = 0;
        public final double servoReturnPosition = 0.5;
        public final double servoMaxPWM = 2.5;
        public final double servoMaxDeadband = 0;
        public final double servoCenterPWM = 0;
        public final double servoDeadbandMin = 0;
        public final double servoMinPWM = 0.5;
        public final double matchTimeForEndgame = 30;
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

    public final class Limelight {
        public final double kP = .01;
        public final double kI = 0.0;
        public final double kD = 0.0;
        public final double tolerance = .5;

        public final double angleSetpointDegrees = 0.0;
        public final double mountingAngleDegrees = 45.0;
        public final double mountingHeightMeters = 0.508;
        public final double targetHeightMeters = 2.49555; // the height of the inner circle, not the height you want to
                                                          // get to

    }

    public final class TurnToHeading {
        public final double kP = .019;
        public final double kI = 0;
        public final double kD = 0;
        public final int pidOnTargetTicksGoal = 5;
        public final double pidTolerance = 2.5;

    }
}
