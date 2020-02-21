package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableCommand;
public class VisionAdjustCommand extends TraceableCommand {
    private int m_theta = 45;
    private NetworkTable m_limelight;
    private final IDrivetrainSubsystem m_subsystem;
    private double tx = 10000;
    private double disError = 10000;

    public VisionAdjustCommand(final ILogger logger, IDrivetrainSubsystem subsystem) {
        super(logger);
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
        m_limelight.getEntry("pipeline").setNumber(0);
        m_subsystem = subsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        // network table values
        tx = m_limelight.getEntry("tx").getDouble(0.0);
        double ty = m_limelight.getEntry("ty").getDouble(0.0);
        //NetworkTableEntry ta = m_table.getEntry("ta");

        // update network table values periodically
        //double area = ta.getDouble(0.0);
        if(m_limelight.getEntry("tv").getDouble(0.0) == 1){

            //determine x, y, theta values
            double prefDis = 1; // placeholder number
            double currentDis = (2.49555-0.508) / Math.tan(ty + m_theta);
            //double yDistance = currentDis * Math.sin(m_subsystem.getGyroAngle());
            //double xDistance = currentDis * Math.cos(m_subsystem.getGyroAngle());
            disError = prefDis - currentDis;

            m_subsystem.move(0, 0, tx, false);
            m_subsystem.move(0.5 * (disError/Math.abs(disError)), 0, 0, false);
        }

        SmartDashboard.putNumber("Target Seen", m_limelight.getEntry("tv").getDouble(0.0));
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return Math.abs(disError) < 0.05 && Math.abs(tx) < 0.05;
    }
}