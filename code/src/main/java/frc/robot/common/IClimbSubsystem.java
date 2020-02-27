
package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

import com.google.inject.AbstractModule;

public interface IClimbSubsystem extends Subsystem {

    /**
     * @return the angle of the servo
     */
    public double getAngle();

    /**
     * moves servo to the position where the climber would be secured down
     */
    public void lockServo();

    /**
     * releases arm by turning servo angle
     */
    public void releaseClimber();

    /**
     * can only input negative values between 0 and -1 pulls the robot up
     */
    public void climb(double speed);

    /**
     * 
     * @return speed of the readjustment motor
     */
    public double getSpeed();

}