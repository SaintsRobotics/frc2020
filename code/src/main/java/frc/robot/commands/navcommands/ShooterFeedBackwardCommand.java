package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShooterFeedBackwardCommand extends TraceableCommand {

    private IShooterSubsystem m_subsystem;

    @Inject
    public ShooterFeedBackwardCommand(ILogger logger, IShooterSubsystem subsystem) {
        super(logger);
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_subsystem.feed(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interruped) {
        m_subsystem.stopFeeding();
    }

}