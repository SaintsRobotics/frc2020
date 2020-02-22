/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.strategies;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableCommand;

/**
 * Add your docs here.
 */
public class MoveOffLineStrategyCommand extends TraceableCommand {
    IDrivetrainSubsystem _drivetrain;

    @Inject
    public MoveOffLineStrategyCommand(ILogger logger, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;

        this.addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        new WaitUntilCommand(_drivetrain::isIdle).andThen(new InstantCommand(() -> _drivetrain.moveBackward(5)))
                .schedule();
    }
}
