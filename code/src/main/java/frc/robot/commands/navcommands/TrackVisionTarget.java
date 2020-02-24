/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.commands.DrivetrainCommandBase;
import frc.robot.commands.Util;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class TrackVisionTarget extends DrivetrainCommandBase {
    private final Limelight _limelight;
    private final PIDController _pidController;
    private double _pidOutput;

    @Inject
    public TrackVisionTarget(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
            Limelight limelight) {
        super(logger, config, drivetrain);
        _limelight = limelight;
        _pidController = new PIDController(config.Limelight.kP, config.Limelight.kI, config.Limelight.kD);
        _pidController.setSetpoint(config.Limelight.angleSetpointDegrees);
    }

    @Override
    public void initialize() {
        super.initialize();
        _limelight.setLEDState(3);
    }

    @Override
    protected double getRotation() {
        SmartDashboard.putBoolean("on setpoint ", _pidController.atSetpoint());
        SmartDashboard.putNumber("pid output ", -_pidController.calculate(_limelight.getRotationalOffset()));
        SmartDashboard.putNumber("pid error ", _pidController.getPositionError());

        return -_pidController.calculate(_limelight.getRotationalOffset());
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        _limelight.setLEDState(1);
    }
}
