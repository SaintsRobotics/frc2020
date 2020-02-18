package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.common.IShooterSubsystem;

public class ShooterStartupCommand extends CommandBase {

    private IShooterSubsystem m_subsystem;

    public ShooterStartupCommand(IShooterSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_subsystem.turnOnShooter();
    }

    public boolean isFinished() {
        return true;
    }

}