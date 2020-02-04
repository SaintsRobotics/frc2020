package frc.robot.subsystems;

import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Intake extends TraceableSubsystem implements IIntakeSubsystem {

    public Intake(ILogger logger) {
        super(logger);
    }

    @Override
    public void raiseArm() {
        // TODO Auto-generated method stub

    }

    @Override
    public void lowerArm() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLowered() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void spinIntake(double velocity) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSpinning() {
        // TODO Auto-generated method stub
        return false;
    }
}