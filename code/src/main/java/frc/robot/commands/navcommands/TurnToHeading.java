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
public class TurnToHeading extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final XboxController _controller;
    private double _heading; // TODO **ADD FLUENT API TO SET HEADING!!

    @Inject
    public TurnToHeading(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            XboxController controller) {
        super(logger);
        _drivetrain = drivetrain;
        _controller = controller;

        // TODO **YOU WILL NEED TO ADD A GETTER FOR THE GYRO IN THE SUBSYSTEM. FIGURE
        // OUT IF IT SHOULD BE RADIANS/DEGREES, AND WHICH DIRECTION IS POSITIVE
        addRequirements(_drivetrain);
    }

    /**
     * 
     * @param heading direction robot should face, in degrees. zero is away from the
     *                alliance wall
     * @return returns this for chaining methods
     */
    public TurnToHeading withHeadingDegrees(double heading) {
        // TODO needs implemenation. also, beware of units!!
        return this;
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
