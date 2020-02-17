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
import frc.robot.RobotConfig;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class ShooterCommand extends TraceableCommand {
    private final IShooterSubsystem _shooter;
    private final XboxController _controller;
    private final RobotConfig _config;

    @Inject
    public ShooterCommand(final ILogger logger, IShooterSubsystem shooter, RobotConfig config) {
        super(logger);
        _config = config;
        _shooter = shooter;
        _controller = new XboxController(_config.Controller.operatorPort);

        addRequirements(_shooter);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        if (_controller.getAButton()) {
            _shooter.setSpeed(_config.Shooter.speed);
        }
        if (_controller.getBButton()) {
            _shooter.enableFeeding();
        }
        if (_controller.getXButton()) {
            _shooter.disableFeeding();
        }
        if (_controller.getYButton()) {
            _shooter.stopShooter();
        }
        // ==
        // axis??

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
