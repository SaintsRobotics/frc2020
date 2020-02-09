package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class Intake extends TraceableSubsystem implements IIntakeSubsystem {

    public SpeedController intakeController = new PWMVictorSPX(1);
    public SpeedController armController = new PWMVictorSPX(2);
    public DutyCycleEncoder armEncoder = new DutyCycleEncoder(0);
    private final PIDController m_armPIDController = new PIDController(0.1, 0, 0);

    public Intake(final ILogger logger) {
        super(logger);

        intakeController.setInverted(true);
        armController.setInverted(true);
        armEncoder.reset();
        // m_armPIDController.setS

        // Motor is inverted
    }

    public void raiseArm() {
        double count = armEncoder.getDistance();

        // Based on the gear ratio of the motor
        int PulsesPerRevolution;

        // Finding the pulses per revolution will then help find the number of pulses
        // needed to move a quarter of the distance (90deg) needed for the arm
        //int PulsesPerQuarter = PulsesPerRevolution / 4;

        // Arm is pwmVictorspx on port 2
    }

    public void lowerArm() {

    }

    public boolean isLowered() {
        
        return false;
    }

    public void spinIntake(boolean direction) {

        double scaledvelocity = 0;
        if (direction) {
            scaledvelocity = 1;
        }
        if (!direction) {
            scaledvelocity = -1;
        }

        intakeController.set(scaledvelocity);

    }

    public void stopIntake() {
        intakeController.set(0);
    }

    public boolean isSpinning() {
        if (Math.abs(intakeController.get()) > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void periodic(){
        SmartDashboard.putNumber("Arm Encoder", armEncoder.get());
    }

}
