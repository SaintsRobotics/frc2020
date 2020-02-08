
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.google.inject.Inject;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotConfig;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Intake extends TraceableSubsystem implements IIntakeSubsystem {

    private RobotConfig _config;
    public SpeedController intakeController;
    public SpeedController armController;
    public Encoder armEncoder;
    private final PIDController m_armPIDController = new PIDController(0.1, 0, 0);
    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {
        super(logger);
        _config = config;
        intakeController = new PWMVictorSPX(config.Intake.intakeControllerChannel);
        armController = new PWMVictorSPX(config.Intake.armControllerChannel);
        armEncoder = new Encoder(config.Intake.armEncoderChannelA, config.Intake.armEncoderChannelB);

        intakeController.setInverted(true);
        armController.setInverted(true);
        armEncoder.reset();

        // m_armPIDController.setS

        // Motor is inverted
    }

    public void raiseArm() {
        int count = armEncoder.get();

        // Based on the gear ratio of the motor
        int pulsesPerRevolution = 0;

        // Finding the pulses per revolution will then help find the number of pulses
        // needed to move a quarter of the distance (90deg) needed for the arm
        int pulsesPerQuarter = pulsesPerRevolution / 4;

        double distancePerPulse = armEncoder.getDistancePerPulse();
        // Arm is pwmVictorspx on port 2
    }

    public void lowerArm() {

    }

    public boolean isLowered() {

        boolean isArmStopped = armEncoder.getStopped();
        // NOT Finished
        // DEFAULT RETURN FALSE
        return false;

    }

    public void spinIntake(boolean direction) {

        double scaledVelocity = 0;
        if (direction) {
            scaledVelocity = 1;
        }
        if (!direction) {
            scaledVelocity = -1;
        }

        intakeController.set(scaledVelocity);

    }

    public void stopIntake() {
        intakeController.set(0);
    }

    public boolean isSpinning() {
        return Math.abs(intakeController.get()) > 0;
    }
}
