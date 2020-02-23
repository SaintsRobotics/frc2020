package frc.robot.subsystems;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;
import com.google.inject.Inject;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;

public class ClimbSubsystem extends TraceableSubsystem implements IClimbSubsystem {

    private Servo servoMotor;
    private CANSparkMax winchMotor;
    private double releasePosition;
    private double returnPosition;

    @Inject
    public ClimbSubsystem(final ILogger logger, RobotConfig config) {
        super(logger);

        servoMotor = new Servo(1);
        winchMotor = new CANSparkMax(19, MotorType.kBrushless);

    }

    public double getAngle() {
        return servoMotor.get();
    }

    public void releaseArm() {
        servoMotor.set(this.releasePosition);
    }

    public void returnServo() {
        servoMotor.set(this.returnPosition);
    }

    public void pullArm(double speed) {
        winchMotor.set(speed);
    }

    public double getSpeed() {
        return winchMotor.getEncoder().getVelocity();
    }

}
