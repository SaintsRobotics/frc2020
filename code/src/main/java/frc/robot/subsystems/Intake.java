
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Intake extends TraceableSubsystem implements IIntakeSubsystem {

    public SpeedController intakeController = new PWMVictorSPX(1);
    public SpeedController armController = new PWMVictorSPX(2);
    public Encoder armEncoder = new Encoder(1, 1);
    private final PIDController m_armPIDController = new PIDController(0.1, 0, 0);

    public Intake(final ILogger logger) {
        super(logger);

        intakeController.setInverted(true);
        armController.setInverted(true);
        armEncoder.reset();
        // m_armPIDController.setS

        // Motor is inverted
    }

    public void raiseArm() {
        int count = armEncoder.get();

        // Based on the gear ratio of the motor
        int PulsesPerRevolution;

        // Finding the pulses per revolution will then help find the number of pulses
        // needed to move a quarter of the distance (90deg) needed for the arm
        int PulsesPerQuarter = PulsesPerRevolution / 4;

        double distancePerPulse = armEncoder.getDistancePerPulse();
        // Arm is pwmVictorspx on port 2
    }

    public void lowerArm() {

    }

    public boolean isLowered() {
        boolean isArmStopped = armEncoder.getStopped();

    }

    public void spinIntake(boolean direction) {

        double scaledvelocity;
        if (direction) {
            scaledvelocity = 1;
        }
        if (!direction) {
            scaledvelocity = -1;
        }

        intakeController.set(scaledvelocity);

    }

    public void stopIntake() {
        intakeController.set(0);
    }

    public boolean isSpinning() {
        if (Math.abs(intakeController.get()) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
