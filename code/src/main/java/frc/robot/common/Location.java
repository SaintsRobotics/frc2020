/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.Inject;

import frc.robot.sim.MatchSimulator;

/**
 * Tracks the current position of the robot
 */
public class Location {
    private Position _position = new Position(0, 0);
    private double _speed = 0;
    private double _heading = 0;
    private MatchSimulator _simulator;
    private ILogger _logger;

    @Inject
    public Location(ILogger logger, MatchSimulator simulator) {
        _simulator = simulator;
        _logger = logger;
    }

    public Position getPosition() {
        return _position;
    }

    public double getSpeed() {
        return _speed;
    }

    public double getHeading() {
        return _heading;
    }

    public void updatePosition(Position pos) {
        _position = pos;
        this.updateSimulator();
    }

    public void updateSpeed(double speed) {
        _speed = speed;
    }

    public void updateHeading(double heading) {
        _heading = heading;
        this.updateSimulator();
    }

    private void updateSimulator() {
        _simulator.updateLocation(_position.getX(), _position.getY(), _heading);
        _logger.information(this.toString());
    }

    @Override
    public String toString() {
        return "x: " + _position.getX() + ", y: " + _position.getY() + ", heading: " + _heading;
    }
}
