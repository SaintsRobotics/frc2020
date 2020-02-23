package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.*;

public class DriveArmCommand extends TraceableCommand {

    private final IIntakeSubsystem _intake;
    private final XboxController _controller;

    @Inject

    public DriveArmCommand(final ILogger logger, IIntakeSubsystem intakeSubsystem) {
        super(logger);
        _intake = intakeSubsystem;
        _controller = new XboxController(1);
        addRequirements(_intake);

    }

    public void initialize() {
        super.initialize();
    }

    public void execute() {
        double x = _controller.getY(Hand.kLeft);
        if (x > 0) {
            x *= .3;
        } else {
            x *= .75;
        }
        _intake.setArmMotor(x);
        SmartDashboard.putNumber("arm controller ", x);
    }

    public boolean isFinished() {
        return false;
    }

}