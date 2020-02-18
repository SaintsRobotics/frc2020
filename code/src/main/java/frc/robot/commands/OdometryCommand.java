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
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final IDrivetrainSubsystem m_subsystem;
    private final Pose2d m_target;
    private PIDController m_x;
    private PIDController m_y;
    private double m_xOutput;
    private double m_yOutput;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    @Inject
    public OdometryCommand(ILogger logger, IDrivetrainSubsystem subsystem, Pose2d target, RobotConfig coeffs) {
        super(logger);
        m_subsystem = subsystem;
        m_target = target;

        m_x = new PIDController(coeffs.Odometry.pX, coeffs.Odometry.iX, coeffs.Odometry.dX);
        m_y = new PIDController(coeffs.Odometry.pY, coeffs.Odometry.iY, coeffs.Odometry.dY);

        m_x.setTolerance(coeffs.Odometry.toleranceX);
        m_x.disableContinuousInput();
        m_x.reset();

        m_y.setTolerance(coeffs.Odometry.toleranceY);
        m_y.disableContinuousInput();
        m_y.reset();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);


    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        super.initialize();
        m_x.setSetpoint(m_target.getTranslation().getX());
        m_y.setSetpoint(m_target.getTranslation().getY());
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.move(m_x.calculate(m_subsystem.getCurrentPosition().getTranslation().getX()), 
                m_y.calculate(m_subsystem.getCurrentPosition().getTranslation().getY()), 0, true);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_x.reset();
        m_y.reset();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_x.atSetpoint() && m_y.atSetpoint();
    }
}
