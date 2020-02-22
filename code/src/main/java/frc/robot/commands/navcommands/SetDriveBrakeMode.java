/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class SetDriveBrakeMode extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;

    @Inject
    public SetDriveBrakeMode(final ILogger logger, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;

    }

    @Override
    public void execute() {
        super.execute();
        _drivetrain.setToBrakeMode();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
