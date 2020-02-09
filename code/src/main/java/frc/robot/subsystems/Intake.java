
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

    public SpeedController intakeController;
    public SpeedController armController;
    public Encoder armEncoder;
    private final PIDController m_armPIDController = new PIDController(0.1, 0, 0);
    private RobotConfig _config;

    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;
        intakeController = new PWMVictorSPX(config.Intake.intakeControllerPort);
        armController = new PWMVictorSPX(config.Intake.armControllerPort);
        armEncoder = new Encoder(config.Intake.armEncoderPortA, config.Intake.armEncoderPortB);

        intakeController.setInverted(true);
        armController.setInverted(true);
        armEncoder.reset();

        // m_armPIDController.setS

        // Motor is inverted
    }

    // Raises the intake arm
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

    // Lowers the arm
    public void lowerArm() {

    }

    // Checks if arm is currently lowered
    public boolean isLowered() {

        boolean isArmStopped = armEncoder.getStopped();
        // NOT Finished
        // DEFAULT RETURN FALSE
        return false;

    }

    // Spin the intake to accept balls into robot
    public void spinIntake() {
        intakeController.set(1);
    }

    // Reverse the intake to push balls away from intake
    public void reverseIntake() {
        intakeController.set(-1);
    }

    // Stops the intake
    public void stopIntake() {
        intakeController.set(0);
    }

    // Checks if intake is spinning
    public boolean isSpinning() {
        return Math.abs(intakeController.get()) > 0;
    }
}
