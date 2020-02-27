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
    private double endgameTime;

    @Inject
    public ClimbSubsystem(final ILogger logger, RobotConfig config) {
        super(logger);

        this.servoMotor = new Servo(config.Climber.servoPort);
        this.winchMotor = new CANSparkMax(config.Climber.winchPort, MotorType.kBrushless);
        this.servoMotor.setBounds(config.Climber.servoMaxPWM, config.Climber.servoMaxDeadband,
                config.Climber.servoCenterPWM, config.Climber.servoDeadbandMin, config.Climber.servoMinPWM);
        this.endgameTime = config.Climber.matchTimeForEndgame;
        this.releasePosition = config.Climber.servoReleasePosition;
        this.returnPosition = config.Climber.servoReturnPosition;
    }

    public double getAngle() {
        return servoMotor.get();
    }

    public void releaseClimber() {
        // If there is more than 30 seconds left in the match, we're not allowed to
        // release the climber
        if (DriverStation.getInstance().getMatchTime() > this.endgameTime) {
            return;
        }
        this.servoMotor.set(this.releasePosition);
        DriverStation.reportError("climb released ", false);
    }

    public void lockServo() {
        this.servoMotor.set(this.returnPosition);
    }

    public void climb(double speed) {
        this.winchMotor.set(speed);
    }

    public double getSpeed() {
        return this.winchMotor.getEncoder().getVelocity();
    }

}
