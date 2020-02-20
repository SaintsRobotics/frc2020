package frc.robot.common;

public class MathUtilities {
    
    /** method to ensure radian value isn't negative or out of range */
    public static double getPositiveRadians (double num) {
        return ((num % (2 * Math.PI)) + (2 * Math.PI)) % (2*Math.PI); 
    }

}