package frc.robot.common;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ILedSubsystem extends Subsystem {

    /**
     * doesn't have safety checks
     * 
     * @param background The color to set the background of the led strip
     * @param foreground The color to set the foreground of the led strip
     */
    public void setColors(Color background, Color foreground);

}