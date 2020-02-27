/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.strategies;

import com.google.inject.Inject;

import frc.robot.commands.navcommands.MoveBackwardsCommand;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableCommand;

/**
 * Add your docs here.
 */
public class MoveOffLineStrategyCommand extends TraceableCommand {
    MoveBackwardsCommand _moveBackwards;

    @Inject
    public MoveOffLineStrategyCommand(ILogger logger, MoveBackwardsCommand moveBackwardsCommand) {
        super(logger);
        _moveBackwards = moveBackwardsCommand;
    }

    @Override
    public void initialize() {
        _moveBackwards.forDistance(1).schedule();
    }
}
