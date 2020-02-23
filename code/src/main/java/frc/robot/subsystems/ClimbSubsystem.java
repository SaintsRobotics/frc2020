package frc.robot.subsystems;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConfig;
import frc.robot.commands.DriveArmCommand;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;
import com.google.inject.Inject;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;

public class ClimbSubsystem extends TraceableSubsystem implements IClimbSubsystem {

    private Servo servoMotor;
    private CANSparkMax winchMotor;
    private double releasePosition;
    private double returnPosition;

    @Inject
    public ClimbSubsystem(final ILogger logger, RobotConfig config) {
        super(logger);

        servoMotor = new Servo(config.Climber.servoPort);
        winchMotor = new CANSparkMax(config.Climber.winchPort, MotorType.kBrushless);
        this.releasePosition = config.Climber.servoReleasePosition;
        this.returnPosition = config.Climber.servoReturnPosition;
    }

    public double getAngle() {
        return servoMotor.get();
    }

    public void releaseClimber() {
        servoMotor.set(this.releasePosition);
        DriverStation.reportError("climb released ", false);
    }

    public void lockServo() {
        servoMotor.set(this.returnPosition);
    }

    public void climb(double speed) {
        winchMotor.set(speed);
    }

    public double getSpeed() {
        return winchMotor.getEncoder().getVelocity();
    }

}
