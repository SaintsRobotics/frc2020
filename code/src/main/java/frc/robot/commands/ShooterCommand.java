/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Robot;
import frc.robot.RobotConfig;
import frc.robot.common.*;
import frc.robot.subsystems.ShootOneBallCommand;
import frc.robot.subsystems.ShooterFeedBackwardCommand;
import frc.robot.subsystems.ShooterShutdownCommand;
import frc.robot.subsystems.ShooterStartupCommand;

/**
 * Add your docs here.
 */
public class ShooterCommand extends TraceableCommand {
    private IShooterSubsystem _subsystem;
    private final XboxController _controller;
    private RobotConfig _config;

    @Inject
    public ShooterCommand(final ILogger logger, IShooterSubsystem subsysem, RobotConfig config) {
        super(logger);
        _subsystem = subsysem;
        _controller = new XboxController(1);
        _config = config;

    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        if (_controller.getAButton()) {
            CommandScheduler.getInstance().schedule(new ShooterStartupCommand(_subsystem));
        }

        if (_controller.getBButton()) {
            CommandScheduler.getInstance().schedule(new ShooterFeedBackwardCommand(_subsystem));
        }

        if (_controller.getXButton()) {
            CommandScheduler.getInstance().schedule(false,
                    new ShootOneBallCommand(_subsystem, _config.Shooter.feederTimeout));
        }

        if (_controller.getXButtonReleased()) {
            CommandScheduler.getInstance().cancel(new ShootOneBallCommand(_subsystem, _config.Shooter.feederTimeout));
        }

        if (_controller.getYButton()) {
            CommandScheduler.getInstance().schedule(new ShooterShutdownCommand(_subsystem));
        }

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
    }
}
