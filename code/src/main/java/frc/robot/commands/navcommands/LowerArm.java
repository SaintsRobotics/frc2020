package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.*;

public class LowerArm extends TraceableCommand {

    private final IIntakeSubsystem _intake;
    private double currentTime = 0;

    @Inject

    public LowerArm(final ILogger logger, IIntakeSubsystem intakeSubsystem) {
        super(logger);
        _intake = intakeSubsystem;
        addRequirements(_intake);
    }

    public void initialize() {
        super.initialize();

    }

    @Override
    public void execute() {
        super.execute();
        _intake.setArmMotor(.8);
        SmartDashboard.putBoolean("lowered?", _intake.isLowered());
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setArmMotor(0);
    }

    public boolean isFinished() {
        return _intake.isLowered();
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