/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.RobotConfig;
import frc.robot.RobotConfig.SwerveDrivetrain;
import frc.robot.common.*;

/**
 * An example command that uses an example subsystem.
 */
public class OdometryCommand extends TraceableCommand {
    private final IDrivetrainSubsystem m_subsystem;
    private final Pose2d m_target;
    private PIDController m_xPIDController;
    private PIDController m_yPIDController;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    @Inject
    public OdometryCommand(ILogger logger, IDrivetrainSubsystem subsystem, Pose2d target, RobotConfig config) {
        super(logger);
        m_subsystem = subsystem;
        m_target = target;

        m_xPIDController = new PIDController(config.Odometry.pX, config.Odometry.iX, config.Odometry.dX);
        m_yPIDController = new PIDController(config.Odometry.pY, config.Odometry.iY, config.Odometry.dY);

        m_xPIDController.setTolerance(config.Odometry.toleranceX);
        m_xPIDController.disableContinuousInput();
        m_xPIDController.reset();

        m_yPIDController.setTolerance(config.Odometry.toleranceY);
        m_yPIDController.disableContinuousInput();
        m_yPIDController.reset();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);


    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        super.initialize();
        m_xPIDController.setSetpoint(m_target.getTranslation().getX());
        m_yPIDController.setSetpoint(m_target.getTranslation().getY());
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.move(m_xPIDController.calculate(m_subsystem.getCurrentPosition().getTranslation().getX()), 
                m_yPIDController.calculate(m_subsystem.getCurrentPosition().getTranslation().getY()), 0, true);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_xPIDController.reset();
        m_yPIDController.reset();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_xPIDController.atSetpoint() && m_yPIDController.atSetpoint();
    }
}
