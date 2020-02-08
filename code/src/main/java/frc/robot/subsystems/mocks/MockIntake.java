/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockIntake extends TraceableMockSubsystem implements IIntakeSubsystem {
    private boolean _isRaised = false;
    private boolean _isSpinning = false;

    public MockIntake(ILogger logger) {
        super(logger);
    }

    @Override
    public void raiseArm() {
        // the spinner will be shut down when the arm is raised
        _isSpinning = false;

        _isRaised = true;
    }

    @Override
    public void lowerArm() {
        _isRaised = false;
    }

    @Override
    public boolean isLowered() {
        return !_isRaised;
    }

    @Override
    public void spinIntake(double velocity) {
        _isSpinning = true;
    }

    @Override
    public boolean isSpinning() {
        return _isSpinning;
    }
}