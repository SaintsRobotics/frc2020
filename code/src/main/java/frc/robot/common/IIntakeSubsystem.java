package frc.robot.common;

import com.google.inject.AbstractModule;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotConfig.SwerveDrivetrain;

public interface IIntakeSubsystem extends Subsystem {

    // Engages the intake arm to be parallel with the ground
    void raiseArm();

    // Disengages arm to be normal with the ground
    void lowerArm();

    /**
     * 
     * @return whether or not the intake arm is currently lowered
     */
    boolean isLowered();

    /*
     * @param specified speed of the intake (negative velocity used for reversing
     * intake)
     */
    void spinIntake(double velocity);

    /**
     * 
     * @return whether or not the intake is currently spinning
     */
    boolean isSpinning();

}