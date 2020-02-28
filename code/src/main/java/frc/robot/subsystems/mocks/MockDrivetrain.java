/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableMockSubsystem;

public class MockDrivetrain extends TraceableMockSubsystem implements IDrivetrainSubsystem {
    protected boolean _isIdle;

    /**
     * Creates a new ExampleSubsystem.
     */
    @Inject
    public MockDrivetrain(ILogger logger) {
        super(logger);

        _isIdle = true;

    }

    protected void setIdle(boolean idle) {
        _isIdle = idle;
    }

    @Override
    public double getMinSpeed() {
        return 0;
    }

    @Override
    public double getMaxSpeed() {
        return .5;
    }

    @Override
    public double getMaxAngularSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void move(double x, double y, double rotation, boolean fieldRelative) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetGyro() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setToBrakeMode() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setToCoastMode() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getGyroAngle() {
        // TODO Auto-generated method stub
        return 0;
    }

}