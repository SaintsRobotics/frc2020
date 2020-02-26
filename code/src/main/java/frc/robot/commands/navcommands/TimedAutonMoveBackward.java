/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.RobotConfig;

import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class TimedAutonMoveBackward extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;

    private final RobotConfig _config;
    private double currentTime = 0;
    private double x, y, r;
    private double _targetTime = 1;
    private double _velocity = 1.5;

    @Inject
    public TimedAutonMoveBackward(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;

        _config = config;
        addRequirements(_drivetrain);
    }

    public TimedAutonMoveBackward withTime(double time) {
        _targetTime = time;
        return this;
    }

    public TimedAutonMoveBackward withVelocity(double velocity) {
        _velocity = velocity;
        return this;
    }

    @Override
    public void initialize() {
        super.initialize();
        currentTime = 0;
    }

    @Override
    public void execute() {
        super.execute();

        x = _velocity;
        y = 0;

        r = 0;
        _drivetrain.move(x, y, r, true);
        currentTime += .02;

    }

    /**
     * 
     */
    @Override
    public boolean isFinished() {
        return currentTime >= _targetTime;
    }

}