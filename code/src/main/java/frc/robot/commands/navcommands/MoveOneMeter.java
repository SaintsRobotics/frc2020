/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotConfig;

import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class MoveOneMeter extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final ILogger _logger;
    private final RobotConfig _config;
    private double currentTime = 0;
    private double x, y, r;
    private double _targetTime = 1;
    private double _velocity = 1;
    private double _heading = 180;

    @Inject
    public MoveOneMeter(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;
        _logger = logger;
        _config = config;
        addRequirements(_drivetrain);
    }

    public MoveOneMeter withHeading(double heading) {
        _heading = heading;
        return this;
    }

    @Override
    public void initialize() {
        super.initialize();
        new SequentialCommandGroup(
                new TimedMoveHeading(_logger, _config, _drivetrain).withHeading(_heading).withTime(1).withVelocity(1),
                new TimedMoveHeading(_logger, _config, _drivetrain).withHeading(_heading).withTime(.5).withVelocity(.2))
                        .schedule();
    }

    /**
     * 
     */
    @Override
    public boolean isFinished() {
        return currentTime >= _targetTime;
    }

}