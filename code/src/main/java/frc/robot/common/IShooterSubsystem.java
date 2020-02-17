/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface IShooterSubsystem extends Subsystem {

    /**
     * starts the shooter
     *
     */

    void turnOnShooter();

    /**
     * basically, this makes sure one ball is yeeted, if feedbackward is false if
     * feedbackward is true, it just drives the feeder in the opposite direction,
     * probably becasue mech didn't fix their problems, and the balls got jammed
     */
    void feed(boolean feedBackward);

    /**
     * stops feeding the shooter balls
     */
    void stopFeeding();

    /**
     * turns off the shooter motors
     */
    void turnOffShooter();

    /**
     * for the shoot one ball command to know when to be finished
     */
    boolean getHasShotBall();

}