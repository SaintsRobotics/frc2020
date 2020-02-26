/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.RobotConfig;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class GoToPosition extends TraceableCommand {
    private final IDrivetrainSubsystem _drivetrain;
    private Pose2d _position;

    @Inject
    public GoToPosition(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger);
        _drivetrain = drivetrain;

        // TODO **YOU WILL NEED TO ADD A GETTER FOR THE POSITION IN THE SUBSYSTEM.
        // FIGURE OUT IF IT IS A LOCATION OBJECT OR POSE2D
        addRequirements(_drivetrain);
    }

    /**
     * 
     * @param position a pose 2d representing the x and y coordinates, along with
     *                 heading of the robot Represents forward velocity w.r.t the
     *                 robot frame of reference.
     * @return an instance of this so the methods can be chained
     */
    public GoToPosition withPosition(Pose2d position) {
        // TODO needs implemenation, also figure out frame of reference
        _position = position;
        return this;
    }

    public GoToPosition withPosition(double x, double y, double degrees) {
        return this.withPosition(new Pose2d(x, y, Rotation2d.fromDegrees(degrees)));
    }

    protected Pose2d getPosition() {
        return _position;
    }

    @Override
    public void initialize() {
        super.initialize();
        // TODO needs implementation
    }

    @Override
    public void execute() {
        super.execute();
        // TODO needs implementation

    }

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {
        return false;
        // TODO needs implementation
    }
}
