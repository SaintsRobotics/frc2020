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
    private final RobotConfig _config;

    @Inject
    public DrivetrainControllerCommand(final ILogger logger, IDrivetrainSubsystem drivetrain, RobotConfig config) {
        super(logger);
        _drivetrain = drivetrain;
        _controller = new XboxController(0);
        _config = config;
        addRequirements(_drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();

    }

    @Override
    public void execute() {
        super.execute();

        double x = Util.oddSquare(Util.deadZones(_controller.getY(Hand.kLeft), _config.Controller.kDriveDeadzone))
                * _drivetrain.getMaxSpeed();
        double y = Util.oddSquare(Util.deadZones(_controller.getX(Hand.kLeft), _config.Controller.kDriveDeadzone))
                * _drivetrain.getMaxSpeed();
        double r = Util.oddSquare(Util.deadZones(_controller.getX(Hand.kRight), _config.Controller.kTurnDeadzone)
                * _drivetrain.getMaxAngularSpeed());

        _drivetrain.move(x * _config.Controller.kDriveScale, y * _config.Controller.kDriveScale,
                r * _config.Controller.kTurnScale, !_controller.getBumper(Hand.kRight));

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
