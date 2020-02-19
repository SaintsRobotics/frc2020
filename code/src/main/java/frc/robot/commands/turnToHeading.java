package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.RobotConfig;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableCommand;

public class turnToHeading extends TraceableCommand {
    private Pose2d[] path;
    private IDrivetrainSubsystem m_subsystem;
    private OdometryCommand m_command;

    @Inject
    public turnToHeading(ILogger logger, IDrivetrainSubsystem subsystem, RobotConfig config, double heading) {
        super(logger);
        addRequirements(m_subsystem);
        m_subsystem = subsystem;
        path[0] = new Pose2d(m_subsystem.getCurrentPosition().getTranslation(), new Rotation2d(heading));
        m_command = new OdometryCommand(logger, subsystem, path, config);

    }

    public void initialize() {
        m_command.initialize();
    }

    public void execute() {
        m_command.execute();
    }

    public boolean isFinished() {
        return m_command.isFinished();
    }
}