/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.AbstractModule;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotConfig.SwerveDrivetrain;

/**
 * The drivetrain subsystem. This forms the basis for any drivetrain such as
 * swerve, tankdrive etc.
 * 
 * All distances and speeds are in meters or meters/second.
 */
public interface IDrivetrainSubsystem extends Subsystem {

    /**
     * @return the minimum speed the robot can travel in meters per second
     */
    double getMinSpeed();

    /**
     * @return the maximum speed the robot can travel in meters per second
     */
    double getMaxSpeed();

    Pose2d getCurrentPosition();

    // used for teleop
    void move(double x, double y, double rotation, boolean fieldRelative);

    void resetGyro();

    void setToBrake(boolean brake);

    double getMaxXAcceleration();

    double getMaxYAcceleration();

    double getMaxThetaAcceleration();
}

// distance
// position
// direction
// constants
