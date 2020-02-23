/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotConfig;
import frc.robot.commands.Util;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class TrackVisionTarget extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private final double _angleSetpoint;
    private final XboxController _controller;
    private final Limelight _limelight;
    private final PIDController _pidController;
    private double _pidOutput;
    private RobotConfig _config;

    @Inject
    public TrackVisionTarget(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            XboxController controller, Limelight limelight) {
        super(logger);
        _drivetrain = drivetrain;
        _angleSetpoint = config.Limelight.angleSetpointDegrees;
        _controller = controller;
        _limelight = limelight;
        _pidController = new PIDController(config.Limelight.kP, config.Limelight.kI, config.Limelight.kD);
        _config = config;
        addRequirements(_drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();
        // TODO needs implementation
    }

    @Override
    public void execute() {
        super.execute();
        _pidOutput = _pidController.calculate(_limelight.getRotationalOffset());
        double x = Util.oddSquare(Util.deadZones(_controller.getY(Hand.kLeft), _config.Controller.kDriveDeadzone))
                * _drivetrain.getMaxSpeed();
        double y = Util.oddSquare(Util.deadZones(_controller.getX(Hand.kLeft), _config.Controller.kDriveDeadzone))
                * _drivetrain.getMaxSpeed();
        _drivetrain.move(x * _config.Controller.kDriveScale, y * _config.Controller.kDriveScale, _pidOutput,
                !_controller.getBumper(Hand.kRight));
        // TODO needs implementation

    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
        // TODO needs implementation
    }
}
