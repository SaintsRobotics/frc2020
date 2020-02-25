
package frc.robot.commands;

public class Util {
    /**
     * 
     * @param input    value to be modified (deadzoned)
     * @param deadZone the maximum value to be considered zero
     * @return the modified version of input
     */
    public static double deadZones(double input, double deadZone) {
        if (Math.abs(input) < deadZone) {
            return 0;
        }
        return input;
    }

    /**
     * note: dilate controller, then deadzone it
     * 
     * @param input controller input
     * @return the dilated value of the input
     */
    public static double oddSquare(double input) {
        if (input >= 0) {
            return input * input;
        } else {
            return input * -input;
        }
    }

}