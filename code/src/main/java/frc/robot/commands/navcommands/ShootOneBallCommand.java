package frc.robot.commands.navcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShootOneBallCommand extends TraceableCommand {

    private IShooterSubsystem m_subsystem;
    private Double m_timeout;
    private Timer m_timer;

    public ShootOneBallCommand(ILogger logger, IShooterSubsystem subsystem) {
        super(logger);
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
        m_timer = new Timer();
    }

    public ShootOneBallCommand withTimeout(Double seconds) {
        m_timeout = new Double(seconds);
        return this;
    }

    @Override
    public void initialize() {
        if (m_timeout == null) {
            DriverStation.reportError("withTimeout() method must be called first!!", true);
        }
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        m_subsystem.feed(false);
    }

    public boolean isFinished() {
        return m_subsystem.getHasShotBall() || m_timer.get() >= m_timeout;
    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopFeeding();
        m_timer.stop();
    }

}