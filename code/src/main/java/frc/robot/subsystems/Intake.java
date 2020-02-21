
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

    public Encoder armEncoder = new Encoder(9, 8);

    public double upperLimit;
    public double lowerLimit;

    public double lowerArmSpeed;
    public double raiseArmSpeed;

    public double intakeSpeed;
    public double outakeSpeed;

    private RobotConfig _config;

    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;

        intakeController = new WPI_VictorSPX(config.Intake.intakeControllerPort);
        armController = new WPI_VictorSPX(config.Intake.armControllerPort);

        armController.setInverted(true);

        upperLimit = config.Intake.armInnerSetpoint;
        lowerLimit = config.Intake.armLowerSetpoint;

        lowerArmSpeed = _config.Intake.lowerArmSpeed;
        raiseArmSpeed = _config.Intake.raiseArmSpeed;

        intakeSpeed = _config.Intake.intakeSpeed;
        outakeSpeed = _config.Intake.outakeSpeed;
    }

    public boolean armIsAtTop() {
        return armEncoder.get() >= upperLimit;
    }

    public boolean armIsAtBottom() {
        return armEncoder.get() <= lowerLimit;
    }

    /**
     * @param input a value from -1 to 1, fed directly to the speed controller
     */
    public void raiseArm() {
        this.armController.set(raiseArmSpeed);
    }

    public void lowerArm() {
        this.armController.set(lowerArmSpeed);
    }

    public void spinIntake() {
        intakeController.set(.6);
    }

    public void spinOutake() {
        intakeController.set(.6);
    }

    public void stopIntake(){
        intakeController.set(0);
    }

    public double getIntakeSpeed(){
        return intakeController.get();
    }
}
