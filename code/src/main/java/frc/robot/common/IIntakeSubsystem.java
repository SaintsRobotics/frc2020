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

    // Spin the intake to accept balls into robot
    void spinIntake();

    // Reverse the intake to push balls away from robot
    void reverseIntake();

    // Stop intake
    void stopIntake();

    /**
     * 
     * @return whether or not the intake is currently spinning
     */
    boolean isSpinning();

}