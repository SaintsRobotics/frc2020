/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockShooter extends TraceableMockSubsystem implements IShooterSubsystem {

    private boolean _isReady = false;
    private boolean _shotFired = false;

    public MockShooter(ILogger logger) {
        super(logger);
    }

    /*
     *
     * @param targetVelocity the rpm of shooter
     */
    @Override
    public void startShooter(double targetVelocity) {
        _isReady = true;
        this.getLogger().debug("startShooter");
    }

    @Override
    public boolean isReadyToShoot() {
        return _isReady;
    }

    @Override
    public void shoot() {
        // as there's no delay a shot will always be fired
        _shotFired = false;
        if (this.isReadyToShoot()) {
            _shotFired = true;
            this.getLogger().information("shot fired");
        }
    }

    @Override
    public boolean shotFired() {
        return _shotFired;
    }

    @Override
    public void stopShooter() {
        _isReady = false;
        _shotFired = false;
        this.getLogger().debug("stopShooter");
    }
}