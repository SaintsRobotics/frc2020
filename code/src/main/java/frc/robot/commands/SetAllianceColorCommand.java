package frc.robot.commands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.common.ILedSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableCommand;

public class SetAllianceColorCommand extends TraceableCommand {

    private ILedSubsystem ledSubsystem;
    private Alliance alliance;

    @Inject
    public SetAllianceColorCommand(final ILogger logger, ILedSubsystem ledSubsystem) {
        super(logger);

        this.ledSubsystem = ledSubsystem;
        this.alliance = Alliance.Invalid;

        this.runWhenDisabled();
        addRequirements(ledSubsystem);
    }

    public void initialize() {
        super.initialize();
    }

    public void execute() {
        Alliance currentAlliance = DriverStation.getInstance().getAlliance();
        if (alliance == currentAlliance) {
            return;
        } else {
            alliance = currentAlliance;
            ledSubsystem.setPreset(alliance);
        }

    }

    public boolean isFinished() {
        return false;
    }

}