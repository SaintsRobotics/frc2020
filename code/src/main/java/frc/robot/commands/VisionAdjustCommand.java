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
import edu.wpi.first.wpilibj.XboxController;
public class VisionAdjustCommand extends TraceableCommand {
    private int m_theta = 45; // placeholder value
    private NetworkTable m_limelight;
    private double targetSeen = 0.0;
    private final IDrivetrainSubsystem m_subsystem;
    private XboxController m_controller;

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
        if(m_limelight.getEntry("tv").getDouble(0.0) == 1){
            NetworkTableEntry tx = m_limelight.getEntry("tx");
            NetworkTableEntry ty = m_limelight.getEntry("ty");
            NetworkTableEntry ta = m_limelight.getEntry("ta");
            NetworkTableEntry tv = m_limelight.getEntry("tv");

            // update network table values periodically
            double x = tx.getDouble(0.0);
            double y = ty.getDouble(0.0);
            double area = ta.getDouble(0.0);
            targetSeen = tv.getDouble(0.0);
            SmartDashboard.putNumber("Target Seen", targetSeen);
            
            //determine x, y, theta values
            double prefDis = 1; // placeholder number
            double currentDis = (2.49555-0.508) / Math.tan(y + m_theta); 
            double kPDis = 0.1; // placeholder kP
            double disError = prefDis - currentDis;
            if(m_controller.getAButton() && disError > 0.05){ // change button later, placeholder
                // double disAdjust = kPDis*disError;
                
                m_subsystem.move(0, 2, 0, false); // double check x or y, which one will move
            }
        }   
        // 
        
        SmartDashboard.putNumber("Target Seen", targetSeen);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
