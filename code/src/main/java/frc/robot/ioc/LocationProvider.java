/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ioc;

import com.google.inject.Inject;
import com.google.inject.Provider;

import frc.robot.common.ILogger;
import frc.robot.common.Location;
import frc.robot.sim.MatchSimulator;

/**
 * Add your docs here.
 */
public class LocationProvider implements Provider<Location> {
    ILogger _logger;
    MatchSimulator _simulator;

    @Inject
    public void ControllerProvider(ILogger logger, MatchSimulator simulator) {
        _logger = logger;
        _simulator = simulator;
    }

    @Override
    public Location get() {
        return new Location(_logger, _simulator);
    }
}
