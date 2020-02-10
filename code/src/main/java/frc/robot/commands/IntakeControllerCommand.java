package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import frc.robot.RobotConfig;
import frc.robot.common.*;

public class IntakeControllerCommand extends TraceableCommand {

    private final IIntakeSubsystem _intake;
    private final XboxController _controller;
    private final RobotConfig _config;
    @Inject

    public IntakeControllerCommand(final ILogger logger, IIntakeSubsystem intakeSubsystem, RobotConfig config) {
        super(logger);
        _config = config;
        _intake = intakeSubsystem;
        _controller = new XboxController(_config.Controller.operatorPort);
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