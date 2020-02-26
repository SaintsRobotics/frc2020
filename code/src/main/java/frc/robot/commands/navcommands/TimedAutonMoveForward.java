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
public class TimedAutonMoveForward extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;

    private final RobotConfig _config;
    private double currentTime = 0;
    private double x, y, r;
    private double _targetTime = 1;

    @Inject
    public TimedAutonMoveForward(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            IShooterSubsystem shooter) {
        super(logger);
        _drivetrain = drivetrain;

        _config = config;
        addRequirements(_drivetrain);
    }

    public TimedAutonMoveForward withTime(int time) {
        _targetTime = time;
        return this;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();

        x = -1;
        y = 0;

        r = 0;
        _drivetrain.move(x, y, r, true);

    }

    /**
     * 
     */
    @Override
    public boolean isFinished() {
        return currentTime >= _targetTime;
    }

}