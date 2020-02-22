/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotConfig;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class GoToPosition extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;

    @Inject
    public GoToPosition(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;

        // TODO **YOU WILL NEED TO ADD A GETTER FOR THE POSITION IN THE SUBSYSTEM.
        // FIGURE OUT IF IT IS A LOCATION OBJECT OR POSE2D
        addRequirements(_drivetrain);
    }

    // TODO ADD FLUENT API METHOD CHAINING TO SET POSITION

    @Override
    public void initialize() {
        super.initialize();
        // TODO needs implementation
    }

    @Override
    public void execute() {
        super.execute();
        // TODO needs implementation

    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
        // TODO needs implementation
    }
}
