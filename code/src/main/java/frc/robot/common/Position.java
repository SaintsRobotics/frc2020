package frc.robot.common;

/**
 * The field relative position of the robot
 */
public class Position {
    private double _x = 0.0;
    private double _y = 0.0;

    public Position(double x, double y) {
        _x = x;
        _y = y;
    }

    /**
     * 
     * @return the field relative x coordinate of the robot
     */
    public double getX() {
        return _x;
    }

    /**
     * 
     * @return the field relative y coordinate of the robot
     */
    public double getY() {
        return _y;
    }

    @Override
    public String toString(){
        return "x: " + _x + ", y: " + _y;
    }

}