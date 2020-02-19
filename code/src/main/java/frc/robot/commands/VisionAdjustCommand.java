/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.common.NullLogger;
import com.google.inject.Inject;
import frc.robot.common.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionAdjustCommand extends TraceableCommand {
    private NetworkTable m_table;
    private int m_theta;

    public VisionAdjustCommand(final ILogger logger) {
        super(logger);
        m_table = NetworkTableInstance.getDefault().getTable("limelight");
        m_table.getEntry("pipeline").setNumber(0);
    }

    @Override
    public void initialize() {

    }

    public void execute() {

        // network table values
        NetworkTableEntry tx = m_table.getEntry("tx");
        NetworkTableEntry ty = m_table.getEntry("ty");
        NetworkTableEntry ta = m_table.getEntry("ta");
        NetworkTableEntry tv = m_table.getEntry("tv");

        // update network table values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double targetSeen = tv.getDouble(0.0);
    }

    public void end(boolean interrupted) {

    }

    public boolean isFinished() {
        return false;
    }

}
