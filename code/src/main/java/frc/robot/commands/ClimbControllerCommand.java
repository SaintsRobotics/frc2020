package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IClimbSubsystem;

import frc.robot.common.*;

public class ClimbControllerCommand extends TraceableCommand {

    private final IClimbSubsystem _climb;
    private final XboxController _controller;

    @Inject
    public ClimbControllerCommand(final ILogger logger, IClimbSubsystem climbSubsystem) {
        super(logger);
        _climb = climbSubsystem;
        _controller = new XboxController(0);
        addRequirements(_climb);
    }

    public void initialize() {
        super.initialize();
    }

    public void execute() {
        if (_controller.getAButtonPressed()) {
            _climb.releaseArm();

        }
        _climb.pullArm(_controller.getTriggerAxis(Hand.kLeft) - _controller.getTriggerAxis(Hand.kRight));

    }

    public boolean isFinished() {
        return false;
    }

}