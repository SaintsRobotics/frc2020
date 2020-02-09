/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ioc;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotConfig;

/**
 * Add your docs here.
 */
public class ControllerProvider implements Provider<XboxController> {
    private RobotConfig _config;

    @Inject
    public ControllerProvider(RobotConfig config) {
        _config = config;
    }

    @Override
    public XboxController get() {
        return new XboxController(_config.Controller.controllerPort);
    }
}