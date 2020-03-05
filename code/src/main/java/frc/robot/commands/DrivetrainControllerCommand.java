/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import frc.robot.RobotConfig;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class DrivetrainControllerCommand extends DrivetrainCommandBase {

    @Inject
    public DrivetrainControllerCommand(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger, config, drivetrain);
    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    protected double getRotation() {
        return Util.oddSquare(Util.deadZones(_controller.getX(Hand.kRight), _config.Controller.kTurnDeadzone))
                * _config.Controller.kTurnScale;
    }
}
