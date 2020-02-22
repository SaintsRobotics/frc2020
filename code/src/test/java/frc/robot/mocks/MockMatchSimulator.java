/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.mocks;

import frc.robot.sim.MatchSimulator;

/**
 * Add your docs here.
 */
public class MockMatchSimulator extends MatchSimulator {
    @Override
    public void updateLocation(double x, double y, double heading) {
        // NO OP
    }
}
