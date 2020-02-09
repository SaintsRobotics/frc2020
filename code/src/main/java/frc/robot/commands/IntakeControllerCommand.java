package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import frc.robot.common.*;

public class IntakeControllerCommand extends TraceableCommand {

    private final IIntakeSubsystem _intake;
    private final XboxController _controller;

    @Inject

    public IntakeControllerCommand(final ILogger logger, IIntakeSubsystem intakeSubsystem) {
        super(logger);
        _intake = intakeSubsystem;
        _controller = new XboxController(1);
        addRequirements(_intake);

    }

    public void initialize() {
        super.initialize();
    }

    public void execute() {

        _intake.stopIntake();

        if (_controller.getBumper(Hand.kLeft)) {
            _intake.spinIntake();
        }

        if (_controller.getBumper(Hand.kRight)) {
            _intake.reverseIntake();
        }

    }

    public boolean isFinished() {
        return false;
    }
}