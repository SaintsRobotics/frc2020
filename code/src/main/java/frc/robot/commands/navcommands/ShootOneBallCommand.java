package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShootOneBallCommand extends TraceableCommand {

    private IShooterSubsystem m_subsystem;

    @Inject
    public ShootOneBallCommand(ILogger logger, IShooterSubsystem subsystem) {
        super(logger);
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_subsystem.feed(false);
    }

    public boolean isFinished() {
        return m_subsystem.getHasShotBall();
    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopFeeding();
    }

}