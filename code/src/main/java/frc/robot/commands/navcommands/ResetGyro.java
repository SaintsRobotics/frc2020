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
public class ResetGyro extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;

    @Inject
    public ResetGyro(final ILogger logger, IDrivetrainSubsystem drivetrain) {
        super(logger);
        /*
         * This is to prevent edge cases where the TurnToHeading command is running, and
         * in the middle of that, this command gets scheduled. What happens is the gyro
         * is reset, but the setpoint of hte pid isn't updated. By requiring the
         * drivetrain subsystem, the TurnToHeading command is canceled, causing the pid
         * setpoint to (eventually) get reset.
         */
        addRequirements(drivetrain);
        _drivetrain = drivetrain;

    }

    @Override
    public void execute() {
        super.execute();
        _drivetrain.resetGyro();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
