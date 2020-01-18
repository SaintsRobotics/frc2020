/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

/**
 * Tracks the current position of the robot
 */
public interface ILocation {
    Position getPosition();

    double getSpeed();

    double getHeading();

    void updatePosition(Position pos);

    void updateSpeed(double speed);

    void updateHeading(double heading);
}
