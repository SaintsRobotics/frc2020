/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands.mocks;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.RobotConfig;
import frc.robot.commands.navcommands.GoToPosition;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.subsystems.mocks.MockCommandDrivetrain;

/**
 * A mock version of the GotoPosition Command
 */
public class MockGotoPosition extends GoToPosition {
    MockCommandDrivetrain _drivetrain;

    @Inject
    public MockGotoPosition(ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            MockCommandDrivetrain commandDrivetrain) {
        super(logger, config, drivetrain);
        _drivetrain = commandDrivetrain;
    }

    @Override
    public void initialize() {
        Pose2d pos = this.getPosition();
        _drivetrain.followPath(pos);
    }

    @Override
    public void execute() {
        // Ensure its a NO OP
    }

    @Override
    public boolean isFinished() {
        return _drivetrain.isIdle();
    }
}
