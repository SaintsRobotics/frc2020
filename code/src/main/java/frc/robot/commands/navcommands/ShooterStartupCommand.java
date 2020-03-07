package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShooterStartupCommand extends TraceableCommand {

    private IShooterSubsystem m_subsystem;
    private double m_speed;

    @Inject
    public ShooterStartupCommand(ILogger logger, IShooterSubsystem subsystem) {
        super(logger);
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    /**
     * @param speed -1 to 1 speed given to motor controller
     */
    public ShooterStartupCommand withSpeed(double speed) {
        m_speed = speed;
        return this;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_subsystem.turnOnShooter(m_speed);
    }

    public boolean isFinished() {
        return true;
    }

}