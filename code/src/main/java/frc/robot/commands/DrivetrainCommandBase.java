/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import frc.robot.RobotConfig;
import frc.robot.commands.Util;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public abstract class DrivetrainCommandBase extends TraceableCommand {
    protected final IDrivetrainSubsystem _drivetrain;
    protected final XboxController _controller;
    protected final RobotConfig _config;

    protected DrivetrainCommandBase(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
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
                * _drivetrain.getMaxSpeed() * _config.Controller.kDriveScale;
        double y = Util.oddSquare(Util.deadZones(_controller.getX(Hand.kLeft), _config.Controller.kDriveDeadzone))
                * _drivetrain.getMaxSpeed() * _config.Controller.kDriveScale;

        double r = this.getRotation() * _drivetrain.getMaxAngularSpeed();
        // Multiplying the rotating joystick by the max angular speed instead of linear
        // speed because the rotation input is in radians per second

        _drivetrain.move(x, y, r, !_controller.getBumper(Hand.kRight));
    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * IMPORTANT: DO NOT MULTIPLY BY MAX ROTATION SPEED. BUT DO DEADZONE AND
     * TRANSFORM OUPTUT FUNCITON (ex. odd square) IF NEEDED
     */
    protected abstract double getRotation();

    @Override
    public void end(boolean interrupted) {

        super.end(interrupted);

    }
}