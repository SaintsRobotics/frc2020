/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

/**
 * Add your docs here.
 */
public interface IShooterSubsystem {

    /**
     * starts the shooter
     * 
     * @param targetVelocity the target velocity in rpm
     */

    void startShooter(double targetVelocity);

    /**
     * 
     * @return returns true if the shooter is at target velocity and is ready to
     *         shoot
     */
    boolean isReadyToShoot();

    /**
     * Shoots a single ball
     * 
     * @param balls number of balls shot
     */
    void shoot();

    /**
     * determines if the last call to shoot() resulted in a ball being fired. When
     * used to determine the end of a command, should be used with a timeout to
     * account for a ball not firing as expect.
     * 
     * @return true if ball was fired
     */
    boolean shotFired();

    /**
     * turns off the shooter motors
     */
    void stopShooter();

}