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
public class TrackVisionTarget extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final XboxController _controller;
    private final Limelight _limelight;

    // TODO ADD FLUENT API TO SET SETPOINT (remember, it's probably going to be a
    // magic number passed in from config)

    @Inject
    public TrackVisionTarget(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            XboxController controller, Limelight limelight) {
        super(logger);
        _drivetrain = drivetrain;
        _controller = controller;
        _limelight = limelight;
        addRequirements(_drivetrain);
    }

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
     * 
     * @param input    value to be modified (deadzoned)
     * @param deadZone the maximum value to be considered zero
     * @return the modified version of input
     */
    public double deadZones(double input, double deadZone) {
        if (Math.abs(input) < deadZone) {
            return 0;
        }
        return input;
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
