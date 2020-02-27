/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.commands.Util;
import frc.robot.common.ILogger;
import frc.robot.common.Location;
import frc.robot.common.TraceableMockSubsystem;

public class MockCommandDrivetrain extends TraceableMockSubsystem {
    private Location _location;
    protected boolean _isIdle;

    /**
     * Creates a new ExampleSubsystem.
     */
    @Inject
    public MockCommandDrivetrain(ILogger logger, Location location) {
        super(logger);

        _location = location;
        _isIdle = true;

    }

    protected void setIdle(boolean idle) {
        _isIdle = idle;
    }

    public boolean isIdle() {
        return _isIdle;
    }

    public double getMaxSpeed() {
        return 2;
    }

    @Override
    public void periodic() {
        super.periodic();
        // This method will be called once per scheduler run
    }

    public void moveForward(double distance) {
        // check for invalid values
        if (this.isPositiveNumber(distance))
            this.updateRelativeLocation(distance);
    }

    public void moveForward(double maxSpeed, double distance) {
        // dont care about the speed for mock purposes
        this.moveForward(distance);
    }

    public void moveBackward(double distance) {
        // check for invalid values
        if (this.isPositiveNumber(distance))
            this.updateRelativeLocation(-distance);

    }

    public void moveBackward(double maxSpeed, double distance) {
        this.moveBackward(distance);
    }

    public void move(double distance) {
        this.updateRelativeLocation(distance);
    }

    public void moveLeft(double distance) {
        this.turnLeft();
        this.moveForward(distance);
    }

    public void moveLeft(double maxSpeed, double distance) {
        this.moveLeft(distance);
    }

    public void moveRight(double distance) {
        this.turnRight();
        this.moveForward(distance);
    }

    public void moveRight(double maxSpeed, double distance) {
        this.moveRight(distance);
    }

    public void moveNorth(double distance) {
        this.faceNorth();
        this.moveForward(distance);
    }

    public void moveNorth(double maxSpeed, double distance) {
        this.moveNorth(distance);
    }

    public void moveSouth(double distance) {
        this.faceSouth();
        this.moveForward(distance);
    }

    public void moveSouth(double maxSpeed, double distance) {
        this.moveSouth(distance);
    }

    public void moveEast(double distance) {
        this.faceEast();
        this.moveForward(distance);
    }

    public void moveEast(double maxSpeed, double distance) {
        this.moveEast(distance);
    }

    public void moveWest(double distance) {
        this.faceWest();
        this.moveForward(distance);
    }

    public void moveWest(double maxSpeed, double distance) {
        this.moveWest(distance);
    }

    public void rotate(double degrees) {
        // TODO Auto-generated method stub

        double heading = _location.getHeading();
        heading = Util.rotate(heading, degrees);
        _location.updateHeading(heading);
    }

    public void turnLeft() {
        this.rotate(-90);
    }

    public void turnRight() {
        this.rotate(90);
    }

    public void faceNorth() {
        _location.updateHeading(0);
    }

    public void faceSouth() {
        _location.updateHeading(180);
    }

    public void faceEast() {
        _location.updateHeading(90);
    }

    public void faceWest() {
        _location.updateHeading(270);
    }

    public void followPath(Pose2d... waypoints) {
        this.followPath(this.getMaxSpeed(), _location.getHeading(), waypoints);
    }

    public void followPath(double maxSpeed, double finalHeading, Pose2d... waypoints) {

        for (int i = 0; i < waypoints.length; i++) {
            double distance = Util.distanceBetweenPoints(_location.getPosition(), waypoints[i]);
            this.updateRelativeLocation(distance);
        }
        _location.updateHeading(finalHeading);
    }

    protected void updateRelativeLocation(double distance) {
        this.updateRelativeLocation(distance, true);
    }

    protected void updateRelativeLocation(double distance, boolean enableIdling) {
        if (enableIdling && !_isIdle) {
            this.getLogger().warning("Can't initiate a move while a move is in progress.");
            return;
        }

        if (enableIdling)
            _isIdle = false;

        Pose2d position = Util.calculateRelativePosition(distance, _location);

        _location.updatePosition(position.getTranslation().getX(), position.getTranslation().getY());
        if (enableIdling)
            _isIdle = true;
    }

    private boolean isPositiveNumber(double value) {
        if (value < 0) {
            this.getLogger().error("Expected a value > 0, but received: " + value);
            return false;
        }
        return true;
    }
}
