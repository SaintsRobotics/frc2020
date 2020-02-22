
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

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

    public DutyCycleEncoder armEncoder = new DutyCycleEncoder(7);

    private final PIDController m_armPIDController = new PIDController(5, 0, 0);
    private RobotConfig _config;

    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;

        intakeController = new WPI_VictorSPX(config.Intake.intakeControllerPort);
        armController = new WPI_VictorSPX(config.Intake.armControllerPort);

        m_armPIDController.setTolerance(0.005);
        m_armPIDController.setSetpoint(0.115);

        armController.setInverted(true);

        // m_armPIDController.setS

        // Motor is inverted
    }

    // Raises the intake arm
    public void raiseArm() {
        m_armPIDController.setSetpoint(_config.Intake.armInnerSetpoint);
    }

    // Lowers the arm

    public void lowerArm() {
        m_armPIDController.setSetpoint(_config.Intake.armLowerSetpoint);
        DriverStation.reportError("Lower Arm", false);
    }

    public void setArmMotor(double speed) {
        armController.set(speed);
    }

    // Checks if arm is currently lowered
    public boolean isLowered() {
        return armEncoder.get() > .3 && armEncoder.get() < .32;

    }

    // Spin the intake to accept balls into robot
    public void spinIntake() {
        intakeController.set(.6);
    }

    // Reverse the intake to push balls away from intake
    public void reverseIntake() {
        intakeController.set(-.6);

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
