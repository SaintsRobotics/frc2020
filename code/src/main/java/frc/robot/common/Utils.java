package frc.robot.common;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class Utils {

    public static double rotate(double original, double degrees) {
        double heading = original + degrees;
        heading = heading >= 360 ? heading - 360 : heading < 0 ? heading + 360 : heading;
        return heading;
    }

    public static Pose2d calculateRelativePosition(double distance, Location location) {
        double x = 0;
        double y = 0;
        double heading = location.getHeading();
        if (heading == 0) {
            x = distance;
        } else {
            double radianHeading = Math.toRadians(heading);
            x = Math.sin(radianHeading) * distance;
            y = Math.cos(radianHeading) * distance;
        }

        double newXPos = location.getX() + x;
        double newYPos = location.getY() + y;

        // constrain the movement
        if (newXPos < 0) {
            newXPos = 0;
        }
        if (newYPos < 0) {
            newYPos = 0;
        }

        return new Pose2d(newXPos, newYPos, Rotation2d.fromDegrees(location.getHeading()));
    }

    public static double distanceBetweenPoints(Pose2d start, Pose2d end) {
        double c = 0;
        double a = end.getTranslation().getX() - start.getTranslation().getX();
        double b = end.getTranslation().getY() - start.getTranslation().getY();

        c = Math.sqrt((a * a) + (b * b)) * (a < 0 || b < 0 ? -1 : 1);
        return c;
    }
}