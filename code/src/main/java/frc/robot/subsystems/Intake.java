
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotConfig;

import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Intake extends TraceableSubsystem implements IIntakeSubsystem {

    public SpeedController intakeController;
    public SpeedController armController;

    public DutyCycleEncoder armEncoder;
    private final PIDController m_armPIDController = new PIDController(5, 0, 0);
    private RobotConfig _config;

    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;
        intakeController = new PWMVictorSPX(config.Intake.intakeControllerPort);
        armController = new PWMVictorSPX(config.Intake.armControllerPort);
        armEncoder = new DutyCycleEncoder(_config.Intake.armEncoderPort);


        intakeController.setInverted(_config.Intake.intakeInverted);

        intakeController = new WPI_VictorSPX(config.Intake.intakeControllerPort);
        armController = new WPI_VictorSPX(config.Intake.armControllerPort);

        m_armPIDController.setTolerance(_config.Intake.armTolerance);
        m_armPIDController.setSetpoint(_config.Intake.armInnerSetpoint);

        armController.setInverted(_config.Intake.armInverted);

        // m_armPIDController.setS

        // Motor is inverted
    }

    // Raises the intake arm
    public void raiseArm() {
        // int count = armEncoder.get();

        // Based on the gear ratio of the motor
        int pulsesPerRevolution = 0;

        // Finding the pulses per revolution will then help find the number of pulses
        // needed to move a quarter of the distance (90deg) needed for the arm
        int pulsesPerQuarter = pulsesPerRevolution / 4;

        // double distancePerPulse = armEncoder.getDistancePerPulse();
        // Arm is pwmVictorspx on port 2
    }


    // Lowers the arm

    public void lowerArm() {
        m_armPIDController.setSetpoint(_config.Intake.armLowerSetpoint);
        DriverStation.reportError("Lower Arm", false);
    }

    // Checks if arm is currently lowered
    public boolean isLowered() {

        // boolean isArmStopped = armEncoder.getStopped();
        // NOT Finished
        // DEFAULT RETURN FALSE
        // return false;
        return armEncoder.get() > _config.Intake.isLoweredLowThreshhold && armEncoder.get() < _config.Intake.isLoweredHighThreshhold;

    }

    // Spin the intake to accept balls into robot
    public void spinIntake() {
        intakeController.set(_config.Intake.intakeForwardSpeed);
    }

    // Reverse the intake to push balls away from intake
    public void reverseIntake() {
        intakeController.set(_config.Intake.intakeReverseSpeed);

    }

    // Stops the intake
    public void stopIntake() {
        intakeController.set(0);
    }

    // Checks if intake is spinning
    public boolean isSpinning() {
        return Math.abs(intakeController.get()) > 0;
    }

    public void periodic() {
        SmartDashboard.putNumber("Arm Encoder", armEncoder.get());
        double armOutput = m_armPIDController.calculate(armEncoder.get());
        // armController.set(armOutput);
        SmartDashboard.putNumber("Arm Output", armOutput);
    }

    public void controlledSpinIntake(double amount) {
        intakeController.set(amount);
    }

}
