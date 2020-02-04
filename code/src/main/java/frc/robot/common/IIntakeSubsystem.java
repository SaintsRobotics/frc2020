package frc.robot.common;

import com.google.inject.AbstractModule;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotConfig.SwerveDrivetrain;

public interface IIntakeSubsystem extends Subsystem {

    // Engages the intake arm to be parallel with the ground
    void raiseArm();

    // Disengages arm to be normal with the ground
    void lowerArm();

    /*
     * @param specified speed of the intake (negative velocity used for reversing
     * intake)
     */
    void spinIntake(double velocity);

}