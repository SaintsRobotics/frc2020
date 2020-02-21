package frc.robot.subsystems;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;

public class ClimbSubsystem extends TraceableSubsystem {

    private Servo releaseServo;
    private CANSparkMax pullMotorController;

    public ClimbSubsystem(final ILogger logger) {
        super(logger);

        releaseServo = new Servo(0);
        pullMotorController = new CANSparkMax(1, MotorType.kBrushless);
        // TODO
        // Check if motor is inverted

    }

    public double getAngle() {
        return releaseServo.get();
    }

    public void releaseArm() {
        releaseServo.setAngle(90);
    }

    public void pullArm(double speed) {
        pullMotorController.set(speed);
    }

    public void getSpeed() {
        pullMotorController.getEncoder().getVelocity();
    }

    public void periodic() {
        SmartDashboard.putNumber("Servo angle", getAngle());

    }

}
