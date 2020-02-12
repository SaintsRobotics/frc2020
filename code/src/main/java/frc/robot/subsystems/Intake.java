
package frc.robot.subsystems;




import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.google.inject.Inject;

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

<<<<<<< HEAD
    public Encoder armEncoder;
=======
    public DutyCycleEncoder armEncoder = new DutyCycleEncoder(7);
>>>>>>> origin/master

    private final PIDController m_armPIDController = new PIDController(0.1, 0, 0);
    private RobotConfig _config;

    @Inject
    public Intake(final ILogger logger, final RobotConfig config) {

        super(logger);
        _config = config;
<<<<<<< HEAD
        intakeController = new PWMVictorSPX(config.Intake.intakeControllerPort);
        armController = new PWMVictorSPX(config.Intake.armControllerPort);
        armEncoder = new Encoder(config.Intake.armEncoderPortA, config.Intake.armEncoderPortB);

        intakeController = new PWMVictorSPX(config.Intake.intakeControllerPort);
        armController = new PWMVictorSPX(config.Intake.armControllerPort);
        

=======

        intakeController = new PWMVictorSPX(config.Intake.intakeControllerPort);
        armController = new PWMVictorSPX(config.Intake.armControllerPort);
        

>>>>>>> origin/master
        armController.setInverted(true);
        armEncoder.reset();

        // m_armPIDController.setS

        // Motor is inverted
    }


    // Raises the intake arm
    public void raiseArm() {
      //TODO FINISH ARM METHODS
        double count = armEncoder.getDistance();


        // Based on the gear ratio of the motor
        int pulsesPerRevolution = 0;

        // Finding the pulses per revolution will then help find the number of pulses
        // needed to move a quarter of the distance (90deg) needed for the arm
        int pulsesPerQuarter = pulsesPerRevolution / 4;


        // Arm is pwmVictorspx on port 2
    }

    // Lowers the arm

    public void lowerArm() {

    }


    // Checks if arm is currently lowered
    public boolean isLowered() {

      

        // TODO NOT Finished
        // DEFAULT RETURN FALSE
        return false;

    }


    // Spin the intake to accept balls into robot
    public void spinIntake() {
        intakeController.set(.4);
    }

    // Reverse the intake to push balls away from intake
    public void reverseIntake() {
        intakeController.set(-.4);

    }
    


    // Stops the intake
    public void stopIntake() {
        intakeController.set(0);
    }

    // Checks if intake is spinning
    public boolean isSpinning() {
        return Math.abs(intakeController.get()) > 0;
    }
    public void periodic(){
        SmartDashboard.putNumber("Arm Encoder", armEncoder.get());
    }

    
  public void controlledSpinIntake(double amount){
      intakeController.set(amount);
  }

<<<<<<< HEAD
    @Override
    public void spinIntake(boolean direction) {
        // TODO Auto-generated method stub

    }

=======
>>>>>>> origin/master
}
