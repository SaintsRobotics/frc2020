
package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

import com.google.inject.AbstractModule;

public interface IClimbSubsystem extends Subsystem {

    /**
     * @return the angle of the servo
     */
    public double getAngle();

    public void returnServo();

    /**
     * releases arm by turning servo angle
     */
    public void releaseArm();

    /**
     * pulls the arm down in case of readjustment
     */
    public void pullArm(double speed);

    /**
     * 
     * @return speed of the readjustment motor
     */
    public double getSpeed();

}