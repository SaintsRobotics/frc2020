package frc.robot.common;

public class Utils {

    public static double rotate(double original, double degrees) {
        double heading = original + degrees;
        heading = heading >= 360 ? heading - 360 : heading < 0 ? heading + 360 : heading;
        return heading;
    }
}