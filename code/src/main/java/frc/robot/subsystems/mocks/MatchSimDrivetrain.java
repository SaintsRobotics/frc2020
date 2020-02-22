/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import frc.robot.common.ILogger;
import frc.robot.common.Location;

/**
 * A Mock drivetrain that can be used with the Match Simulator This mock tries
 * to add in delays to simulate (roughly) travel time
 */
public class MatchSimDrivetrain extends MockDrivetrain {
    @Inject
    public MatchSimDrivetrain(ILogger logger, Location location) {
        super(logger, location);
    }

    private final int intervalInMilliseconds = 100;

    @Override
    public double getMaxSpeed() {
        return 1;
    }

    @Override
    protected void updateRelativeLocation(double distance) {
        // rather than transporting directly to the final point, break the distance up
        // into small chunks
        // based on time.

        if (!this.isIdle()) {
            this.getLogger().warning("Can't initiate a move while a move is in progress.");
            return;
        }

        Thread thread = new Thread() {
            public void run() {
                try {
                    setIdle(false);
                    ;
                    double iterations = Math.abs(distance) / getMaxSpeed() * (1000 / intervalInMilliseconds);
                    double distancePerIteration = distance / iterations;
                    for (int i = 0; i < iterations; i++) {
                        updateRelativeLocationBase(distancePerIteration);
                        Thread.sleep(intervalInMilliseconds);
                    }
                } catch (InterruptedException e) {
                    getLogger().error(e);
                } finally {
                    setIdle(true);
                }
            }
        };

        thread.start();
    }

    private void updateRelativeLocationBase(double distance) {
        super.updateRelativeLocation(distance, false);
    }
}
