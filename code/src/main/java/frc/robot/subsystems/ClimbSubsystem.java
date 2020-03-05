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
    private Servo ratchetServo;
    private CANSparkMax winchMotor;
    private RobotConfig _config;
    private double endgameTime;

    @Inject
    public ClimbSubsystem(final ILogger logger, RobotConfig config) {
        super(logger);
        _config = config;
        this.servoMotor = new Servo(config.Climber.releaseServoPort);
        this.ratchetServo = new Servo(_config.Climber.directionServoPort);
        this.winchMotor = new CANSparkMax(config.Climber.winchPort, MotorType.kBrushless);
        this.endgameTime = config.Climber.matchTimeForEndgame;
    }

    public double getAngle() {
        return servoMotor.get();
    }

    public void reverseClimb() {
        this.ratchetServo.set(_config.Climber.winchReverseServoPosition);
        DriverStation.reportError("climb direction reversed", false);
    }

    public void normalClimb() {
        this.ratchetServo.set(_config.Climber.winchNormalServoPosition);
        DriverStation.reportError("climb direction normal", false);
    }

    public void releaseClimber() {
        // If there are more than 30 seconds left in the match, we're not allowed to
        // release the climber
        // if (DriverStation.getInstance().getMatchTime() > this.endgameTime) {
        // return;
        // }
        this.servoMotor.set(_config.Climber.servoReleasePosition);
        DriverStation.reportError("climb released ", false);
    }

    public void lockServo() {
        this.servoMotor.set(_config.Climber.servoReturnPosition);
    }

    public void climb(double speed) {
        this.winchMotor.set(speed);
    }

    public double getSpeed() {
        return this.winchMotor.getEncoder().getVelocity();
    }

}
