/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.common.ILogger;
import frc.robot.common.Location;

/**
 * Moves the robot backwards based on the distance in meters
 */
public class MoveBackwardsCommand extends MoveCommand {

    @Inject
    public MoveBackwardsCommand(ILogger logger, GoToPosition gotoPositionCommand, Location location) {
        super(logger, gotoPositionCommand, location);
    }

    @Override
    public MoveCommand forDistance(double distance) {
        return super.forDistance(-distance);
    }
}
