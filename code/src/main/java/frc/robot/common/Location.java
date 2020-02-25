/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.sim.MatchSimulator;

/**
 * Tracks the current position of the robot
 */
public class Location {
    private Pose2d _position = new Pose2d(0, 0, new Rotation2d(0));
    private double _speed = 0;
    private double _heading = 0;
    private MatchSimulator _simulator;
    private ILogger _logger;

    @Inject
    public Location(ILogger logger, MatchSimulator simulator) {
        _simulator = simulator;
        _logger = logger;
    }

    public Pose2d getPosition() {
        return _position;
    }

    public double getSpeed() {
        return _speed;
    }

    public double getHeading() {
        return _position.getRotation().getDegrees();
    }

    public double getX() {
        return _position.getTranslation().getX();
    }

    public double getY() {
        return _position.getTranslation().getY();
    }

    public void updatePosition(Pose2d pos) {
        _position = pos;
        this.updateSimulator();
    }

    public void updatePosition(double x, double y, double degrees) {
        this.updatePosition(new Pose2d(x, y, Rotation2d.fromDegrees(degrees)));
    }

    public void updatePosition(double x, double y) {
        this.updatePosition(x, y, this.getHeading());
    }

    public void updateSpeed(double speed) {
        _speed = speed;
    }

    public void updateHeading(double heading) {
        this.updatePosition(this.getX(), this.getY(), heading);
        this.updateSimulator();
    }

    private void updateSimulator() {
        _simulator.updateLocation(this.getX(), this.getY(), _heading);
        _logger.information(this.toString());
    }

    @Override
    public String toString() {
        return "x: " + this.getX() + ", y: " + this.getY() + ", heading: " + _heading;
    }
}
