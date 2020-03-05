package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface IIntakeSubsystem extends Subsystem {

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

    void controlledSpinIntake(double amount);

    /**
     * 
     * @return whether or not the intake is currently spinning
     */
    boolean isSpinning();

    /**
     * doesn't have safety checks
     * 
     * @param speed the speed to set the arm motor from -1 to 1
     */
    public void setArmMotor(double speed);

}