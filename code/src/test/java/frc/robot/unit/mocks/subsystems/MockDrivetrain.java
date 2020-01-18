/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.unit.mocks.subsystems;

import com.google.inject.Inject;

import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.Position;
import frc.robot.common.TraceableMockSubsystem;

public class MockDrivetrain extends TraceableMockSubsystem implements IDrivetrainSubsystem {
    /**
     * Creates a new ExampleSubsystem.
     */
    @Inject
    public MockDrivetrain(final ILogger logger) {
        super(logger);
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
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void moveForward(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveForward(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveBackward(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void move(double x, double y, double theta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveNorth(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveSouth(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEast(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveWest(double maxSpeed, double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotate(double degrees) {
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
    public void followPath(double finalHeading, Position... waypoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void followPath(double maxSpeed, double finalHeading, Position... waypoint) {
        // TODO Auto-generated method stub

    }
}
