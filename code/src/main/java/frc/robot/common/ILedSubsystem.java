package frc.robot.common;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ILedSubsystem extends Subsystem {

    /**
     * Sets the color foreground and background color. Will only work if the led
     * stripe preset is set to t2, t3, or t3.
     * 
     * @param background The color to set the background of the led strip
     * @param foreground The color to set the foreground of the led strip
     */
    public void setColors(Color background, Color foreground);

    /**
     * Sets the brightness of the leds with a 0-128 range
     * 
     * @param brighness The brightness to set the leds to.
     */
    public void setBrightness(int brightness);

    /**
     * Sets the leds to the provided Alliance color.
     * 
     * @param alliance The alliance to set the leds too.
     */
    public void setPreset(Alliance alliance);

}