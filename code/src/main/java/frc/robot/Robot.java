/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.navcommands.IntakeIn;
import frc.robot.commands.navcommands.IntakeOut;
import frc.robot.commands.navcommands.ReleaseClimber;
import frc.robot.commands.navcommands.ResetGyro;
import frc.robot.commands.navcommands.SetClimbNormal;
import frc.robot.commands.navcommands.SetClimbReverse;
import frc.robot.commands.navcommands.SetDriveBrakeMode;
import frc.robot.commands.navcommands.SetDriveCoastMode;
import frc.robot.commands.navcommands.ShootOneBallCommand;
import frc.robot.commands.navcommands.ShooterFeedBackwardCommand;
import frc.robot.commands.navcommands.ShooterShutdownCommand;
import frc.robot.commands.navcommands.ShooterStartupCommand;
import frc.robot.commands.navcommands.TrackVisionTarget;
import frc.robot.commands.navcommands.TurnToHeadingCommand;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.Limelight;
import frc.robot.ioc.DependenciesModule;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private SequentialCommandGroup m_autonomousCommand;
  private RobotContainer _robot;
  private RobotConfig _config;
  private XboxController _driverController;
  private XboxController _operatorController;
  private PowerDistributionPanel _pdp;

  private final Injector _container;

  public Robot() {
    this(new DependenciesModule());
  }

  public Robot(AbstractModule dependencies) {
    _container = Guice.createInjector(dependencies);
    _pdp = new PowerDistributionPanel(50);
    _config = _container.getInstance(RobotConfig.class);
    _driverController = new XboxController(_config.Controller.driverControllerPort);
    _operatorController = new XboxController(_config.Controller.operatorControllerPort);
  }

  public RobotContainer getRobot() {
    return _robot;
  }

  public <T> T createInstance(Class<T> type) {
    return _container.getInstance(type);
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    _robot = _container.getInstance(RobotContainer.class);

    // Configure the button bindings
    // these have to be done here as they are not unit testable
    new Limelight(_config).setLEDState(3);
    configureButtonBindings();
    _container.getInstance(IDrivetrainSubsystem.class).resetGyro();
    _container.getInstance(frc.robot.common.Limelight.class).setLEDState(1);

    _container.getInstance(IClimbSubsystem.class).normalClimb();
    _container.getInstance(IClimbSubsystem.class).lockServo();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton gyro = new JoystickButton(_driverController, _config.Controller.resetGyroButtonPort);
    gyro.whenPressed(_container.getInstance(ResetGyro.class));

    JoystickButton idleState = new JoystickButton(_driverController, _config.Controller.driveMotorIdleStateButtonPort);
    idleState.whenPressed(_container.getInstance(SetDriveCoastMode.class));
    idleState.whenReleased(_container.getInstance(SetDriveBrakeMode.class));

    JoystickButton winchDirectionControl = new JoystickButton(_operatorController,
        _config.Controller.climbDirectionSwitchButtonPort);
    winchDirectionControl.whenPressed(_container.getInstance(SetClimbReverse.class));
    winchDirectionControl.whenReleased(_container.getInstance(SetClimbNormal.class));

    JoystickButton start = new JoystickButton(_operatorController, _config.Controller.shooterStartupButtonPort);
    start.whenPressed(_container.getInstance(ShooterStartupCommand.class).withRPM(_config.Shooter.shooterRPM));

    JoystickButton back = new JoystickButton(_operatorController, _config.Controller.feedBackwardButtonPort);
    back.whileHeld(_container.getInstance(ShooterFeedBackwardCommand.class));
    // automatically gets canceled/interrupted when button released.
    // read mouseover on whileHeld method

    JoystickButton feed = new JoystickButton(_operatorController, _config.Controller.feedOneBallButtonPort);
    feed.whileHeld(_container.getInstance(ShootOneBallCommand.class).withTimeout(_config.Shooter.feederTimeoutSeconds));

    JoystickButton shutdown = new JoystickButton(_operatorController, _config.Controller.shooterShutdownButtonPort);
    shutdown.whenPressed(_container.getInstance(ShooterShutdownCommand.class));

    JoystickButton intakeIn = new JoystickButton(_operatorController, _config.Controller.intakeInButtonPort);
    intakeIn.whileHeld(_container.getInstance(IntakeIn.class));

    JoystickButton intakeOut = new JoystickButton(_operatorController, _config.Controller.intakeOutButtonPort);
    intakeOut.whileHeld(_container.getInstance(IntakeOut.class));

    new JoystickButton(_operatorController, _config.Controller.climberReleaseButtonPort)
        .whenPressed(_container.getInstance(ReleaseClimber.class));

    new JoystickButton(_driverController, _config.Controller.generatorSwitchHeadingButtonPort).whenPressed(_container
        .getInstance(TurnToHeadingCommand.class).withHeadingDegrees(_config.Controller.trenchSideGeneratorSwitchAngle));

    JoystickButton visionTrack = new JoystickButton(_driverController, _config.Controller.visionTrackButtonPort);
    visionTrack.whileHeld(_container.getInstance(TrackVisionTarget.class));
    POVButton turnForward = new POVButton(_driverController, _config.Controller.fieldNorthButtonAngle);
    turnForward.whenPressed(_container.getInstance(TurnToHeadingCommand.class)
        .withHeadingDegrees(_config.Controller.fieldNorthButtonAngle));
    POVButton turnRight = new POVButton(_driverController, _config.Controller.fieldEastButtonAngle);
    turnRight.whenPressed(
        _container.getInstance(TurnToHeadingCommand.class).withHeadingDegrees(_config.Controller.fieldEastButtonAngle));
    POVButton turnBack = new POVButton(_driverController, _config.Controller.fieldSouthButtonAngle);
    turnBack.whenPressed(_container.getInstance(TurnToHeadingCommand.class)
        .withHeadingDegrees(_config.Controller.fieldSouthButtonAngle));
    POVButton turnLeft = new POVButton(_driverController, _config.Controller.fieldWestButtonAngle);
    turnLeft.whenPressed(
        _container.getInstance(TurnToHeadingCommand.class).withHeadingDegrees(_config.Controller.fieldWestButtonAngle));

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
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    _robot.robotPeriodic();
    SmartDashboard.putNumber("MatchTime", DriverStation.getInstance().getMatchTime());

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    _robot.disabledInit();

  }

  @Override
  public void disabledPeriodic() {
    _robot.disabledPeriodic();
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = _robot.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    _robot.autonomousPeriodic();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    new Limelight(_config).setLEDState(1);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    _container.getInstance(ShooterShutdownCommand.class).schedule();
    _robot.teleopInit();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    _robot.teleopPeriodic();
    SmartDashboard.putNumber("pdp Voltage", _pdp.getVoltage());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    _robot.teleopInit();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    _robot.testPeriodic();
  }
}
