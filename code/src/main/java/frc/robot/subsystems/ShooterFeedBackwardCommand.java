package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.common.IShooterSubsystem;

public class ShooterFeedBackwardCommand extends CommandBase {

    private IShooterSubsystem m_subsystem;

    public ShooterFeedBackwardCommand(IShooterSubsystem subsystem) {
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