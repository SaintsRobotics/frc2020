/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * The base class from which to build your robot. This class provides a few
 * common features such as logging.
 */
public class CompetitionRobot {
    private final List<Subsystem> _subsystems = new ArrayList<Subsystem>();
    private final ILogger _logger;

    @Inject
    public CompetitionRobot(ILogger logger) {
        _logger = logger;
    }

    public void registerSubsystem(Subsystem subsystem) {
        _subsystems.add(subsystem);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }

    public boolean isSimulation() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isDisabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isTest() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isOperatorControl() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        // TODO Auto-generated method stub

    }

    public void teleopPeriodic() {
        // TODO Auto-generated method stub

    }

    /**
     * Initialization code for test mode should go here.
     *
     * <p>
     * Users should override this method for initialization code which will be
     * called each time the robot enters test mode.
     */
    public void testInit() {
        // TODO Auto-generated method stub

    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopInit() {
        // TODO Auto-generated method stub

    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        // TODO Auto-generated method stub

    }

    /**
     * This autonomous runs the autonomous command selected by your {@link Robot}
     * class.
     */
    public void autonomousInit() {
        // TODO Auto-generated method stub

    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    public void robotPeriodic() {
        // TODO Auto-generated method stub
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    public void disabledInit() {

    }

    public void disabledPeriodic() {
    }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    public void robotInit() {
        // TODO Auto-generated method stub

    }

}
