package frc.robot.commands.navcommands;

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
    private double _heading;


    @Inject
    public turnToHeading(ILogger logger, IDrivetrainSubsystem subsystem, RobotConfig config, OdometryCommand odometryCommand) {
        super(logger);
        addRequirements(m_subsystem);
        m_subsystem = subsystem;

    }

    public turnToHeading withHeading(double heading) {
        _heading = heading;
        return this;

    }
    public void initialize() {
        path[0] = new Pose2d(m_subsystem.getCurrentPosition().getTranslation(), new Rotation2d(_heading));
        m_command.withPath(path);
    }

    public void execute() {
        m_command.execute();
    }

    public boolean isFinished() {
        return m_command.isFinished();
    }
}