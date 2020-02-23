package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.common.*;

public class IntakeOut extends TraceableCommand {

    private final IIntakeSubsystem _intake;

    @Inject

    public IntakeOut(final ILogger logger, IIntakeSubsystem intakeSubsystem) {
        super(logger);
        _intake = intakeSubsystem;

    }

    public void initialize() {
        _intake.reverseIntake();
    }

    public void execute() {

    }

    @Override
    public void end(boolean inerruped) {
        _intake.stopIntake();
    }

    public boolean isFinished() {
        return false;
    }

    /**
     * 
     * @param input    value to be modified (deadzoned)
     * @param deadZone the maximum value to be considered zero
     * @return the modified version of input
     */
    public double deadZones(double input, double deadZone) {
        if (Math.abs(input) < deadZone) {
            return 0;
        }
        return input;
    }

}