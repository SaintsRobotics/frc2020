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

    void setSpeed(double targetVelocity);


    /**
     * 
     * @return returns true if the shooter is at target velocity and is ready to
     *         shoot
     */
    boolean isReady();

    /**
     * feeds the shooter when the shooter is up to speed until told to stop
     */
    void enableFeeding();

    /**
    * stops feeding the shooter balls
    */
    void disableFeeding();

    /**
    *
    * @param balls number of balls shot
    */
    void shootBalls(int balls);
    /**
     * turns off the shooter motors
     */
    void stopShooter();
    
}
