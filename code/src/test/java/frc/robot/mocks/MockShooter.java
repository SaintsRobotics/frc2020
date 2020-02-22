/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.mocks;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockShooter extends TraceableMockSubsystem implements IShooterSubsystem {

    public MockShooter(ILogger logger) {
        super(logger);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void turnOnShooter() {
        // TODO Auto-generated method stub

    }

    @Override
    public void feed(boolean feedBackward) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopFeeding() {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnOffShooter() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getHasShotBall() {
        // TODO Auto-generated method stub
        return false;
    }

}