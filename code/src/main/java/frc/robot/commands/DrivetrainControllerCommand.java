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
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class DrivetrainControllerCommand extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final XboxController _controller;
    private final double _controllerDeadzone = 0.2;

    @Inject
    public DrivetrainControllerCommand(final ILogger logger, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;
        _controller = new XboxController(0);

        addRequirements(_drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();

    }

    @Override
    public void execute() {
        super.execute();

        _drivetrain.move(
                Util.deadZones(_controller.getY(Hand.kLeft) * _drivetrain.getMaxSpeed() * .5, _controllerDeadzone),
                Util.deadZones(_controller.getX(Hand.kLeft) * _drivetrain.getMaxSpeed() * .5, _controllerDeadzone),
                Util.deadZones(_controller.getX(Hand.kRight) * _drivetrain.getMaxAngularSpeed() * .5,
                        _controllerDeadzone),
                _controller.getBumper(Hand.kRight));

        // Multiplying the rotating joystick by the max angular speed instead of linear
        // speed because the rotation input is in radians per second

    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
