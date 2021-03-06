/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockIntake extends TraceableMockSubsystem implements IIntakeSubsystem {
    private boolean _isRaised = false;
    private boolean _isSpinning = false;

    @Inject
    public MockIntake(ILogger logger) {
        super(logger);
    }

    @Override
    public boolean isLowered() {
        return !_isRaised;
    }

    @Override

    public void spinIntake() {
        _isSpinning = true;
    }

    public void reverseIntake() {
        _isSpinning = true;
    }

    public void stopIntake() {
        _isSpinning = false;

    }

    @Override
    public boolean isSpinning() {
        return _isSpinning;
    }

    @Override
    public void controlledSpinIntake(double amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setArmMotor(double speed) {
        // TODO Auto-generated method stub

    }
}