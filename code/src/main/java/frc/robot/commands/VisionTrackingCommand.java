package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Limelight;
import frc.robot.common.TraceableCommand;

public class VisionTrackingCommand extends TraceableCommand {
    private final IDrivetrainSubsystem m_subsystem;
    private double rotationalOffset;
    private Limelight m_limelight;
    private PIDController m_rotationPID;
    private double m_setpoint;
    private double pidOutput;

    private XboxController c = new XboxController(0);

    public VisionTrackingCommand(final ILogger logger, IDrivetrainSubsystem subsystem, RobotConfig config) {
        super(logger);
        m_limelight = new Limelight(config);
        m_limelight.setLEDState(1);
        m_subsystem = subsystem;
        m_rotationPID = new PIDController(config.Limelight.kP, config.Limelight.kI, config.Limelight.kD);
        m_rotationPID.setTolerance(config.Limelight.tolerance);
        m_rotationPID.reset();
        m_setpoint = config.Limelight.setpoint;
    }

    @Override
    public void initialize() {
        m_limelight.setLEDState(3);
        m_rotationPID.setSetpoint(m_setpoint);
    }

    @Override
    public void execute() {

        SmartDashboard.putBoolean("Target Seen", m_limelight.isTargetSeen());
        rotationalOffset = m_limelight.getRotationalOffset();

        pidOutput = m_rotationPID.calculate(rotationalOffset);
        SmartDashboard.putNumber("TX", rotationalOffset);
        if (!m_rotationPID.atSetpoint())
            m_subsystem.move(c.getY(Hand.kLeft), c.getX(Hand.kLeft), -pidOutput, true);
        SmartDashboard.putNumber("PID Output Limelight", pidOutput);

        // double yDistance = currentDis * Math.sin(m_subsystem.getGyroAngle());
        // double xDistance = currentDis * Math.cos(m_subsystem.getGyroAngle());

    }

    @Override
    public void end(boolean interrupted) {
        m_limelight.setLEDState(1);
        m_rotationPID.reset();
        m_subsystem.move(0, 0, 0, false);
    }

    @Override
    public boolean isFinished() {
        return m_rotationPID.atSetpoint();
    }
}