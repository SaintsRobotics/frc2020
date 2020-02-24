package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IClimbSubsystem;

import frc.robot.common.*;

/**
 * drives the climber manually
 */
public class ReleaseClimber extends TraceableCommand {

    private final IClimbSubsystem _climb;

    @Inject
    public ReleaseClimber(final ILogger logger, IClimbSubsystem climbSubsystem) {
        super(logger);
        _climb = climbSubsystem;
        addRequirements(_climb);
    }

    @Override
    public void execute() {
        _climb.releaseClimber();
    }

    public boolean isFinished() {
        return true;
    }

}