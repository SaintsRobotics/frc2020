/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.strategies;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.common.ILogger;
import frc.robot.common.Location;
import frc.robot.common.TraceableCommand;

/**
 * Add your docs here.
 */
public class Easy23StrategyCommand extends TraceableCommand {
    MoveOffLineStrategyCommand _moveOfflineCommand;
    Location _location;

    @Inject
    public Easy23StrategyCommand(ILogger logger, Location location, MoveOffLineStrategyCommand moveOfflineCommand) {
        super(logger);
        _moveOfflineCommand = moveOfflineCommand;
        _location = location;
    }

    @Override
    public void initialize() {
        new InstantCommand(() -> {
            _location.updatePosition(12.53, 5.83);
            _location.updateHeading(0);
        }).andThen(_moveOfflineCommand).schedule();
    }
}
