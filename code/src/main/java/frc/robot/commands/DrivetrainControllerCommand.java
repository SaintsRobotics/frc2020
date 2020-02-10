/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import frc.robot.RobotConfig;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class DrivetrainControllerCommand extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final XboxController _controller;
    private final RobotConfig _Config;

    @Inject
    public DrivetrainControllerCommand(final ILogger logger, IDrivetrainSubsystem drivetrain, final RobotConfig config) {
        super(logger);
        _Config = config;
        _drivetrain = drivetrain;
        _controller = new XboxController(_Config.Controller.driverPort);

        addRequirements(_drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        _drivetrain.move(deadZones(_controller.getY(Hand.kLeft) * _drivetrain.getMaxSpeed(), _Config.Controller.leftXdeadzone),
                deadZones(_controller.getX(Hand.kLeft) * _drivetrain.getMaxSpeed(), _Config.Controller.leftYdeadzone),
                deadZones(_controller.getX(Hand.kRight) * _drivetrain.getMaxSpeed(), _Config.Controller.rightXdeadzone),
                _controller.getBumper(Hand.kRight)); // theta
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
