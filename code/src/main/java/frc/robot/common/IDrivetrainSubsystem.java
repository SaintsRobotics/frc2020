/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

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

    void moveForward(double distance);

    void moveForward(double maxSpeed, double distance);

    void moveBackward(double distance);

    void moveBackward(double maxSpeed, double distance);

    // used for teleop
    void move(double x, double y, double rotation);

    void moveLeft(double distance);

    void moveLeft(double maxSpeed, double distance);

    // robot relative
    void moveRight(double distance);

    // robot relative
    void moveRight(double maxSpeed, double distance);

    // field relative
    void moveNorth(double distance);

    // field relative
    void moveNorth(double maxSpeed, double distance);

    // field relative
    void moveSouth(double distance);

    // field relative
    void moveSouth(double maxSpeed, double distance);

    // field relative
    void moveEast(double distance);

    // field relative
    void moveEast(double maxSpeed, double distance);

    // field relative
    void moveWest(double distance);

    // field relative
    void moveWest(double maxSpeed, double distance);

    void rotate(double degrees);

    void turnLeft();

    void turnRight();

    void faceNorth();

    void faceSouth();

    void faceEast();

    void faceWest();

    void followPath(double finalHeading, Position... waypoint);

    void followPath(double maxSpeed, double finalHeading, Position... waypoint);
}

// distance
// position
// direction
// constants
