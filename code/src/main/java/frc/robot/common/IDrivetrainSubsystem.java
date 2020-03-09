/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import edu.wpi.first.wpilibj.geometry.Translation2d;
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
     * @return the maximum speed the robot can travel in <b>meters per second<b>
     */
    double getMaxSpeed();

    /**
     * angular velocity is calculated by dividing the tangential velocity,
     * getMaxSpeed(), by the radius (half the diagonal of the bot)
     * 
     * @return the maximum speed of the robot if it was just spinning in place, in
     *         <b> radians per second <b>
     */
    double getMaxAngularSpeed();

    // used for teleop
    /**
     * The pivot point of the bot when rotating around its center is 0, 0.
     * 
     * @param x             Represents forward velocity w.r.t the robot frame of
     *                      reference. (Fwd is +)
     * @param y             Represents sideways velocity w.r.t the robot frame of
     *                      reference. (Left is +)
     * @param rotation      Represents the angular velocity of the robot frame. (CCW
     *                      is +)
     * @param fieldRelative
     * 
     */
    void move(double x, double y, double rotation, boolean fieldRelative);

    /**
     * 
     * @param x                Represents forward velocity w.r.t the robot frame of
     *                         reference. (Fwd is +)
     * @param y                Represents sideways velocity w.r.t the robot frame of
     *                         reference. (Left is +)
     * @param rotation         Represents the angular velocity of the robot frame.
     *                         (CCW is +)
     * @param fieldRelative
     * @param pivotPointMeters The point around which the bot rotates in meters,
     *                         with (0, 0) being it's center. The pivot point
     *                         defaults to zero if no parameter is provided.
     */
    void move(double x, double y, double rotation, boolean fieldRelative, Translation2d pivotPointMeters);

    void resetGyro();

    /**
     * calling this will set the idle state of the drive motor controllers to break,
     * and will be able to stop "on a dime." the motors won't continue to spin from
     * momentum
     */
    void setToBrakeMode();

    /**
     * calling this will set the idle state of the drive motor controllers to coast,
     * and will continue to spin if there is momentum still trying to spin the
     * motors. The result of this is the robot will continue to drift in the
     * direction it was going because nothing is opposing its momentum
     */
    void setToCoastMode();

    double getGyroAngle();

}
