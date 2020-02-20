/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.common.NullLogger;
import com.google.inject.Inject;

import frc.robot.RobotConfig;
import frc.robot.common.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
public class VisionAdjustCommand extends TraceableCommand {
    private int m_theta = 45; // placeholder value
    private NetworkTable m_limelight;
    private final IDrivetrainSubsystem m_subsystem;
    private XboxController m_controller;
    private OdometryCommand m_command;
    private Pose2d[] path;

    public VisionAdjustCommand(final ILogger logger, IDrivetrainSubsystem subsystem, RobotConfig config, Pose2d distance) {
        super(logger);
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
        m_limelight.getEntry("pipeline").setNumber(0);
        m_subsystem = subsystem;

        if(m_limelight.getEntry("tv").getDouble(0.0) == 1.0){
            //double xAngle = m_limelight.getEntry("tx").getDouble(0.0);
            double yAngle = m_limelight.getEntry("ty").getDouble(0.0);
            //double area = m_limelight.getEntry("ta").getDouble(0.0);
            
            //determine x, y, theta values
            double currentDis = (2.49555-0.508) / Math.tan(Math.toRadians(yAngle + m_theta));
            double yDistance = currentDis * Math.sin(subsystem.getGyroAngle());
            double xDistance = currentDis * Math.cos(subsystem.getGyroAngle());
            path[0] = new Pose2d(xDistance, yDistance, new Rotation2d((Math.PI / 2) - subsystem.getGyroAngle()));
        }
        SmartDashboard.putNumber("Target Seen", m_limelight.getEntry("tv").getDouble(0.0));
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
