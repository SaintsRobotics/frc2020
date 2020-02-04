/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.mocks;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;
import frc.robot.TestDependenciesModule;
import frc.robot.RobotConfig;

/**
 * A Mocked version of the Real RobotHost. This class acts as a simple
 * replacement for the real host, providing periodic calls.
 */
public class FakeRobot {
    private Command m_autonomousCommand;
    private RobotContainer _robot;
    private RobotConfig _config;

    private final Injector _container = Guice.createInjector(new TestDependenciesModule());
    // The driver's controller
    private int _period;
    private Mode _lastMode = Mode.kNone;
    private Mode _currentMode = Mode.kTeleop;

    public FakeRobot() {
        _config = this.createInstance(RobotConfig.class);
        _robot = this.createInstance(RobotContainer.class);
    }

    public <T> T createInstance(Class<T> type) {
        return _container.getInstance(type);
    }

    public RobotContainer getRobot() {
        return _robot;
    }

    public static enum Mode {
        kNone, kDisabled, kAutonomous, kTeleop, kTest
    }

    public Mode getCurrentMode() {
        return _currentMode;
    }

    protected void setMode(Mode mode) {
        _currentMode = mode;
    }

    public void startCompetition(int matchLengthInMilliseconds) {
        this.startCompetition(matchLengthInMilliseconds, Mode.kTeleop, 20);
    }

    public void startCompetition(int matchLengthInMilliseconds, Mode mode) {
        this.startCompetition(matchLengthInMilliseconds, mode, 20);
    }

    public void startCompetition(int matchLengthInMilliseconds, Mode mode, int period) {
        _period = period;
        this.setMode(mode);
        robotInit();

        long hardStop = System.currentTimeMillis() + matchLengthInMilliseconds;

        // Loop forever, calling the appropriate mode-dependent function
        while (true) {
            if (hardStop >= System.currentTimeMillis()) {
                break;
            }
            loopFunc();
            // simulate a timed control loop
            try {
                Thread.sleep(_period);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        _robot = _container.getInstance(RobotContainer.class);

        // Configure the button bindings
        // these have to be done here as they are not unit testable
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // while we can call the command here directly, its recommended to delegate
        // the selection of the command to the Robot class ie
        // exampleButton.whenPressed(_robot.whenButtonXPressed());
        // this allows the unit testing of the whenButtonXPressed via the FakeRobot
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
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        _robot.robotInit();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    public void disabledInit() {
        _robot.disabledInit();
    }

    public void disabledPeriodic() {
        _robot.disabledPeriodic();
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    public void autonomousInit() {
        m_autonomousCommand = _robot.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        _robot.autonomousPeriodic();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }

        _robot.teleopInit();
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        _robot.teleopPeriodic();
    }

    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
        _robot.teleopInit();
    }

    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        _robot.testPeriodic();
    }

    protected void loopFunc() {
        // Call the appropriate function depending upon the current robot mode
        if (_currentMode == Mode.kDisabled) {
            // Call DisabledInit() if we are now just entering disabled mode from either a
            // different mode
            // or from power-on.
            if (_lastMode != Mode.kDisabled) {
                disabledInit();
                _lastMode = Mode.kDisabled;
            }

            disabledPeriodic();
        } else if (_currentMode == Mode.kAutonomous) {
            // Call AutonomousInit() if we are now just entering autonomous mode from either
            // a different
            // mode or from power-on.
            if (_lastMode != Mode.kAutonomous) {
                autonomousInit();
                _lastMode = Mode.kAutonomous;
            }

            autonomousPeriodic();
        } else if (_currentMode == Mode.kTeleop) {
            // Call TeleopInit() if we are now just entering teleop mode from either a
            // different mode or
            // from power-on.
            if (_lastMode != Mode.kTeleop) {
                teleopInit();
                _lastMode = Mode.kTeleop;
            }

            teleopPeriodic();
        } else {
            // Call TestInit() if we are now just entering test mode from either a different
            // mode or from
            // power-on.
            if (_lastMode != Mode.kTest) {
                testInit();
                _lastMode = Mode.kTest;
            }

            testPeriodic();
        }

        robotPeriodic();
    }
}
