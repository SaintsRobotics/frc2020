/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.google.inject.Inject;

import frc.robot.RobotConfig;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Position;
import frc.robot.common.TraceableSubsystem;

/**
 * Add your docs here.
 */
public class SwerveDrivetrain extends TraceableSubsystem implements IDrivetrainSubsystem {

    private RobotConfig _config;

    @Inject
    public SwerveDrivetrain(final ILogger logger, final RobotConfig config) {
        super(logger);
        _config = config;
    }

    @Override
    public double getMinSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void moveForward(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveForward(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void move(final double x, final double y, final double theta) {
        // TODO Auto-generated method stub
        this.getLogger().debug("x: " + x + ", y: " + y + ", theta: " + theta);

    }

    @Override
    public void moveLeft(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(final double maxSpeed, final double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotate(final double degrees) {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceNorth() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceSouth() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceEast() {
        // TODO Auto-generated method stub

    }

    @Override
    public void faceWest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(final double finalHeading, final Position... waypoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(final double maxSpeed, final double finalHeading, final Position... waypoint) {
        // TODO Auto-generated method stub

    }
}
