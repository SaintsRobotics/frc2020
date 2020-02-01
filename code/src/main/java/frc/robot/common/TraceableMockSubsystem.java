package frc.robot.common;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class TraceableMockSubsystem implements Subsystem {
    private ILogger _logger;

    /**
     * Creates a new TraceableSubsystem.
     */
    @Inject
    public TraceableMockSubsystem(ILogger logger) {
        _logger = logger;
        logger.setComponentType("Subsystem");
        logger.setComponentName(this.getName());
    }

    public ILogger getLogger() {
        return _logger;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        _logger.debug("periodic");
    }

    public String getName() {
        return getClass().getName();
    }
}