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
    public final RobotConfig.Drivetrain Drivetrain = new RobotConfig.Drivetrain();
    public final RobotConfig.Physical Physical = new RobotConfig.Physical();

    public final class Controller {
        public final int controllerPort = 0;

    }

    public final class Drivetrain {
        // examples to be filled out in detail during drivetrain development
        public final int frontLeftDriveMotorPort = 0;
        public final int rearLeftDriveMotorPort = 2;
        public final int frontRightDriveMotorPort = 4;
        public final int rearRightDriveMotorPort = 6;
    }

    public final class Physical {
        public final double widthInCms = 0;
        public final double lengthInCms = 0;
        public final double weightInKgs = 0;
    }
}
