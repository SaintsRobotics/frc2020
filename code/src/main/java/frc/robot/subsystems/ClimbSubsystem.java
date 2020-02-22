package frc.robot.subsystems;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;
import com.google.inject.Inject;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;

public class ClimbSubsystem extends TraceableSubsystem implements IClimbSubsystem {

    private Servo releaseServo;
    private CANSparkMax pullMotorController;

    @Inject
    public ClimbSubsystem(final ILogger logger) {
        super(logger);

        releaseServo = new Servo(1);
        pullMotorController = new CANSparkMax(19, MotorType.kBrushless);
        // TODO
        // Check if motor is inverted

    }

    public double getAngle() {
        return releaseServo.get();
    }

    public void releaseArm() {
        releaseServo.setAngle(-90);
    }

    public void pullArm(double speed) {
        pullMotorController.set(speed);
    }

    public double getSpeed() {
        return pullMotorController.getEncoder().getVelocity();
    }

}
