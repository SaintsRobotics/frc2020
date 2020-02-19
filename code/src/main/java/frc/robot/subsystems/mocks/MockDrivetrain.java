/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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
    public void move(double x, double y, double theta, boolean fieldRelative) {
        // TODO Auto-generated method stub

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

    @Override
    public Pose2d getCurrentPosition() {
        // TODO Auto-generated method stub
        return new Pose2d(_location.getPosition().getX(), _location.getPosition().getY(),
                new Rotation2d(Math.toRadians(_location.getHeading())));
    }

    @Override
    public double getXAcceleration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getYAcceleration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getThetaAcceleration() {
        // TODO Auto-generated method stub
        return 0;
    }
}
