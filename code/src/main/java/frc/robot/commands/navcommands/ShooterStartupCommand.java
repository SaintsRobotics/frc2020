package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShooterStartupCommand extends TraceableCommand {

    private IShooterSubsystem m_subsystem;
    private int m_rpm;

    @Inject
    public ShooterStartupCommand(ILogger logger, IShooterSubsystem subsystem) {
        super(logger);
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    public ShooterStartupCommand withRPM(int rpm) {
        m_rpm = rpm;
        return this;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_subsystem.turnOnShooter(m_rpm);
    }

    public boolean isFinished() {
        return true;
    }

}