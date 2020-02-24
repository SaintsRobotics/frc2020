package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.common.*;

public class SetAllianceColorCommand extends TraceableCommand {

    private ILedSubsystem ledSubsystem;
    private boolean hasSetColor;

    @Inject
    public SetAllianceColorCommand(final ILogger logger, ILedSubsystem ledSubsystem) {
        super(logger);

        this.ledSubsystem = ledSubsystem;
        this.hasSetColor = false;

        addRequirements(ledSubsystem);
    }

    public void initialize() {
        super.initialize();
    }

    public void execute() {
        if (hasSetColor) {
            return;
        }

        Alliance alliance = DriverStation.getInstance().getAlliance();
        switch (alliance) {
        case Invalid:
            this.ledSubsystem.setColors(Color.kGreen, Color.kWhite);
            break;
        case Red:
            this.ledSubsystem.setColors(Color.kRed, Color.kBlue);
            hasSetColor = true;
            break;
        case Blue:
            this.ledSubsystem.setColors(Color.kBlue, Color.kRed);
            hasSetColor = true;
            break;

        }
    }

    public boolean isFinished() {
        return false;
    }

}