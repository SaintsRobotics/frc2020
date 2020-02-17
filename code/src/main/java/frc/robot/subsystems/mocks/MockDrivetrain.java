/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Location;
import frc.robot.common.Position;
import frc.robot.common.TraceableMockSubsystem;

public class MockDrivetrain extends TraceableMockSubsystem implements IDrivetrainSubsystem {
    private Location _location;

    /**
     * Creates a new ExampleSubsystem.
     */
    @Inject
    public MockDrivetrain(ILogger logger, Location location) {
        super(logger);

        _location = location;
    }

    @Override
    public void periodic() {
        super.periodic();
        // This method will be called once per scheduler run
    }

    @Override
    public double getMinSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void resetGyro() {

    }

    @Override
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void moveForward(double distance) {
        // check for invalid values
        if (this.isPositiveNumber(distance))
            this.updateRelativeLocation(distance);
    }

    @Override
    public void moveForward(double maxSpeed, double distance) {
        // dont care about the speed for mock purposes
        this.moveForward(distance);
    }

    @Override
    public void moveBackward(double distance) {
        // check for invalid values
        if (this.isPositiveNumber(distance))
            this.updateRelativeLocation(-distance);

    }

    @Override
    public void moveBackward(double maxSpeed, double distance) {
        this.moveBackward(-distance);
    }

    @Override
    public void move(double x, double y, double theta, boolean fieldRelative) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(double distance) {
        this.turnLeft();
        // TODO: Calculate the correct path
        this.moveForward(distance);
    }

    @Override
    public void moveLeft(double maxSpeed, double distance) {
        this.moveLeft(distance);
    }

    @Override
    public void moveRight(double distance) {
        this.turnRight();
        // TODO: Calculate the correct path
        this.moveForward(distance);
    }

    @Override
    public void moveRight(double maxSpeed, double distance) {
        this.moveRight(distance);
    }

    @Override
    public void moveNorth(double distance) {
        this.faceNorth();
        this.moveForward(distance);
    }

    @Override
    public void moveNorth(double maxSpeed, double distance) {
        this.moveNorth(distance);
    }

    @Override
    public void moveSouth(double distance) {
        this.faceSouth();
        this.moveForward(distance);

    }

    @Override
    public void moveSouth(double maxSpeed, double distance) {
        this.moveSouth(distance);
    }

    @Override
    public void moveEast(double distance) {
        this.faceEast();
        this.moveForward(distance);
    }

    @Override
    public void moveEast(double maxSpeed, double distance) {
        this.moveEast(distance);
    }

    @Override
    public void moveWest(double distance) {
        this.faceWest();
        this.moveForward(distance);
    }

    @Override
    public void moveWest(double maxSpeed, double distance) {
        this.moveWest(distance);
    }

    @Override
    public void rotate(double degrees) {
        // TODO Auto-generated method stub

        double heading = _location.getHeading();
        heading += degrees;
        _location.updateHeading(heading >= 360 ? heading - 360 : heading < 0 ? heading + 360 : heading);
    }

    @Override
    public void turnLeft() {
        this.rotate(-90);
    }

    @Override
    public void turnRight() {
        this.rotate(90);
    }

    @Override
    public void faceNorth() {
        _location.updateHeading(0);
    }

    @Override
    public void faceSouth() {
        _location.updateHeading(180);
    }

    @Override
    public void faceEast() {
        _location.updateHeading(90);
    }

    @Override
    public void faceWest() {
        _location.updateHeading(270);
    }

    @Override
    public void followPath(double finalHeading, Position... waypoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(double maxSpeed, double finalHeading, Position... waypoints) {

        for (int i = 0; i < waypoints.length; i++) {
            _location.updatePosition(waypoints[i]);
        }
        _location.updateHeading(finalHeading);
    }

    private void updateRelativeLocation(double distance) {
        double x = 0;
        double y = 0;
        double heading = _location.getHeading();
        Position pos = _location.getPosition();
        if (heading == 0) {
            y = distance;
        } else {
            double radianHeading = Math.toRadians(heading);
            x = Math.sin(radianHeading) * distance;
            y = Math.cos(radianHeading) * distance;
        }

        double newXPos = pos.getX() + x;
        double newYPos = pos.getY() + y;

        // constrain the movement
        if (newXPos < 0) {
            newXPos = 0;
        }
        if (newYPos < 0) {
            newYPos = 0;
        }
        _location.updatePosition(new Position(newXPos, newYPos));
    }

    private boolean isPositiveNumber(double value) {
        if (value < 0) {
            this.getLogger().error("Expected a value > 0, but received: " + value);
            return false;
        }
        return true;
    }

    public void setToBrake(boolean brake) {

    }
}
