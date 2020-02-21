
package frc.robot.common;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface IClimbSubsystem extends Subsystem {

    public void ClimbSystem(ILogger logger);

    public double getAngle();

    public void releaseArm();

    public void pullArm(double speed);

    public double getSpeed();

}