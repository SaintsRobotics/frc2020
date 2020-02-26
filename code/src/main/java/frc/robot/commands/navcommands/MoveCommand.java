/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.commands.Util;
import frc.robot.common.ILogger;
import frc.robot.common.Location;
import frc.robot.common.TraceableCommand;

/**
 * Moves the robot based on the distance in meters
 */
public class MoveCommand extends TraceableCommand {
    GoToPosition _gotoPositionCommand;
    Location _location;
    double _distance;

    @Inject
    public MoveCommand(ILogger logger, GoToPosition gotoPositionCommand, Location location) {
        super(logger);
        _gotoPositionCommand = gotoPositionCommand;
        _location = location;
    }

    public MoveCommand forDistance(double distance) {
        _distance = distance;
        return this;
    }

    @Override
    public void initialize() {
        Pose2d position = Util.calculateRelativePosition(_distance, _location);

        _gotoPositionCommand.withPosition(position).schedule();
    }
}
