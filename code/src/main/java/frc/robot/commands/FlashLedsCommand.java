package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.common.ILedSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Limelight;
import frc.robot.common.TraceableCommand;

public class FlashLedsCommand extends TraceableCommand {

    private ILedSubsystem ledSubsystem;
    private Limelight limelight;

    double dB;
    double B;
    double FREQUENCY_CONSTANT = 0.005;

    @Inject
    public FlashLedsCommand(final ILogger logger, ILedSubsystem ledSubsystem, Limelight limelight) {
        super(logger);

        this.ledSubsystem = ledSubsystem;
        this.limelight = limelight;
        addRequirements(ledSubsystem);
    }

    public void initialize() {
        super.initialize();
        this.dB = 0;
        this.B = 0;
    }

    public void execute() {
        if (limelight.getRotationalOffset() > 1) {
            double frequency = 1 / limelight.getRotationalOffset();
            this.dB -= FREQUENCY_CONSTANT * frequency * (B - 0.5);
            this.B += dB;
            int brightness = (int) Math.floor(128 * B);

            this.ledSubsystem.setBrightness(brightness);
        }

    }

    public boolean isFinished() {
        return false;
    }

}