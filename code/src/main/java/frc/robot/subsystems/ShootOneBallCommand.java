package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableCommand;

public class ShootOneBallCommand extends CommandBase {

    private IShooterSubsystem m_subsystem;
    private double m_timeout;
    private Timer m_timer;

    /**
     * 
     * @param timeout the ammount of time in seconds until it says it's finished if
     *                it hasn't actually shot a ball
     */
    public ShootOneBallCommand(IShooterSubsystem subsystem, double timeout) {
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
        m_timeout = timeout;
        m_timer = new Timer();
    }

    @Override
    public void initialize() {
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

    public void end() {
        m_subsystem.stopFeeding();
        m_timer.stop();
    }

    public void interrupted() {
        end();
    }

}