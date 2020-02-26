/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navcommands;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotConfig;
import frc.robot.commands.DrivetrainCommandBase;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class TurnToHeading extends DrivetrainCommandBase {
    private final IDrivetrainSubsystem _drivetrain;
    private final RobotConfig _config;
    private PIDController _PidController;

    private double _pidOutput;
    private double _onTargetTicks;

    @Inject
    public TurnToHeading(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain) {
        super(logger, config, drivetrain);
        _drivetrain = drivetrain;
        _config = config;
        _PidController = new PIDController(_config.turnToHeading.kP, _config.turnToHeading.kI,
                _config.turnToHeading.kD);
        _PidController.enableContinuousInput(0, 360);
        _PidController.setTolerance(_config.turnToHeading.pidTolerance);

        addRequirements(_drivetrain);
    }

    /**
     * 
     * @param heading direction robot should face, in degrees. zero is away from the
     *                alliance wall
     * @return returns this for chaining methods
     */
    public TurnToHeading withHeadingDegrees(double heading) {
        // TODO needs implemenation. also, beware of units!!
        _PidController.setSetpoint(heading);
        return this;
    }

    @Override
    public void initialize() {
        super.initialize();
        _onTargetTicks = 0;

        // TODO needs implementation
    }

    @Override
    public void execute() {

        super.execute();
        if (_PidController.atSetpoint()) {
            _onTargetTicks++;
        } else {
            _onTargetTicks = 0;
        }

    }

    /**
     * 
     * @param input    value to be modified (deadzoned)
     * @param deadZone the maximum value to be considered zero
     * @return the modified version of input
     */

    /**
     * this command controllers a controller so will run forever!
     */
    @Override
    public boolean isFinished() {

        return _onTargetTicks >= _config.turnToHeading.pidOnTargetTicksGoal && _PidController.atSetpoint();
        // TODO needs implementation
    }

    @Override
    protected double getRotation() {
        // TODO Auto-generated method stub
        _pidOutput = _PidController.calculate(_drivetrain.getGyroAngle());
        return _pidOutput;
    }

}
