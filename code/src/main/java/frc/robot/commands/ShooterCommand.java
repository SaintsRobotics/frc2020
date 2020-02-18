/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.sql.Driver;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj2.command.Command;
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
    private ShootOneBallCommand m_shootoneballcommand;
    private ShooterFeedBackwardCommand m_back;
    private ShooterShutdownCommand m_shutdown;
    private ShooterStartupCommand m_start;

    @Inject
    public ShooterCommand(final ILogger logger, IShooterSubsystem subsysem, RobotConfig config) {
        super(logger);
        _subsystem = subsysem;
        _controller = new XboxController(1);
        _config = config;
        this.m_shootoneballcommand = new ShootOneBallCommand(_subsystem, _config.Shooter.feederTimeout);
        this.m_back = new ShooterFeedBackwardCommand(_subsystem);
        this.m_shutdown = new ShooterShutdownCommand(_subsystem);
        this.m_start = new ShooterStartupCommand(_subsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        DriverStation.reportError("shooter command initialized ", false);
    }

    @Override
    public void execute() {
        super.execute();
        if (_controller.getAButton()) {
            CommandScheduler.getInstance().schedule(m_start);
            DriverStation.reportError("shooter startup ", false);
        }

        if (_controller.getBButton()) {
            CommandScheduler.getInstance().schedule(false, m_back);
            DriverStation.reportError("feed backward ", false);
        }

        if (_controller.getBButtonReleased()) {
            CommandScheduler.getInstance().cancel(m_back);
        }

        if (_controller.getXButton()) {
            CommandScheduler.getInstance().schedule(false, m_shootoneballcommand);
            DriverStation.reportError("shoot one ball ", false);

        }

        if (_controller.getXButtonReleased()) {
            CommandScheduler.getInstance().cancel(m_shootoneballcommand);
            DriverStation.reportError("cancel shoot one ball ", false);

        }

        if (_controller.getYButton()) {
            CommandScheduler.getInstance().schedule(m_shutdown);
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
