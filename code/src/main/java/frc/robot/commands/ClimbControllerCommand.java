package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IClimbSubsystem;

import frc.robot.common.*;

/**
 * drives the climber manually
 */
public class ClimbControllerCommand extends TraceableCommand {

    private final IClimbSubsystem _climb;
    private final XboxController _controller;

    @Inject
    public ClimbControllerCommand(final ILogger logger, IClimbSubsystem climbSubsystem) {
        super(logger);
        _climb = climbSubsystem;
        _controller = new XboxController(1);
        addRequirements(_climb);
    }

    public void execute() {
        _climb.climb(Util.deadZones(-_controller.getY(Hand.kRight), .2));
    }

    public boolean isFinished() {
        return false;
    }

}