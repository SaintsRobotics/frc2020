/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.Inject;
import com.google.inject.Provider;

import frc.robot.commands.ClimbControllerCommand;
import frc.robot.commands.DriveArmCommand;
import frc.robot.commands.DrivetrainControllerCommand;
import frc.robot.commands.navcommands.IntakeIn;
import frc.robot.commands.navcommands.LowerArm;
import frc.robot.commands.navcommands.MoveOneMeter;
import frc.robot.commands.navcommands.ResetGyro;
import frc.robot.commands.navcommands.ShootOneBallCommand;
import frc.robot.commands.navcommands.ShooterShutdownCommand;
import frc.robot.commands.navcommands.ShooterStartupCommand;
import frc.robot.commands.navcommands.TimedAutonMoveBackward;
import frc.robot.commands.navcommands.TimedMoveHeading;
import frc.robot.commands.navcommands.TrackVisionTarget;
import frc.robot.common.CompetitionRobot;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.Limelight;
import frc.robot.strategies.Easy23StrategyCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This has the core logic for the Robot. This class must not include any
 * classes that have a dependency on hardware as this will break the ability to
 * unit test.
 */
public class RobotContainer extends CompetitionRobot {

  private Command m_teleopCommand;
  private SequentialCommandGroup m_ThreeBallAuto;
  private SequentialCommandGroup m_LowScore;
  private SequentialCommandGroup m_trenchAuton;
  private RobotConfig _config;
  private Command m_disabledCommand;
  private Provider<Easy23StrategyCommand> _autonomousCommand;

  // private final Provider<DrivetrainControllerCommand> _autonomousCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  @Inject
  private RobotContainer(final ILogger logger, RobotConfig config, IDrivetrainSubsystem drivetrain,
      DrivetrainControllerCommand driveCommand, IIntakeSubsystem intake, IShooterSubsystem shooter,
      DriveArmCommand driveArmCommand, IClimbSubsystem climb, ClimbControllerCommand climbCommand) {
    super(logger);

    _config = config;
    drivetrain.setDefaultCommand(driveCommand);
    intake.setDefaultCommand(driveArmCommand);
    climb.setDefaultCommand(climbCommand);

    m_ThreeBallAuto = new SequentialCommandGroup(
        new ShooterStartupCommand(logger, shooter).withSpeed(_config.Shooter.shooterDefaultRPM),
        new TimedAutonMoveBackward(logger, _config, drivetrain).withTime(.9).withVelocity(1),
        new TimedAutonMoveBackward(logger, _config, drivetrain).withTime(.6).withVelocity(.5).withTimeout(3),
        new TrackVisionTarget(logger, config, drivetrain, new Limelight(config)).withTimeout(4),
        new ShootOneBallCommand(logger, shooter), new ShootOneBallCommand(logger, shooter),
        new ShootOneBallCommand(logger, shooter), new ShooterShutdownCommand(logger, shooter));

    m_LowScore = new SequentialCommandGroup(new ShooterStartupCommand(logger, shooter),
        new TimedMoveHeading(logger, config, drivetrain).withHeading(0).withTime(3.3).withVelocity(1),
        new ShootOneBallCommand(logger, shooter), new ShootOneBallCommand(logger, shooter),
        new ShootOneBallCommand(logger, shooter));

    // TODO finish this auton after Glacier Peak.
    // m_trenchAuton = new SequentialCommandGroup(new ResetGyro(logger, drivetrain),
    // new ShooterStartupCommand(logger, shooter),
    // new TimedAutonMoveBackward(logger, _config,
    // drivetrain).withTime(1.5).withVelocity(1),
    // new TimedAutonMoveBackward(logger, _config,
    // drivetrain).withTime(1).withVelocity(.5),
    // new TrackVisionTarget(logger, config, drivetrain, new Limelight(config)),
    // // .withTimeout(2),
    // new TimedMoveHeading(logger, config,
    // drivetrain).withTime(0.2).withHeading(0),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3));
    // new TurnToHeading(logger, config, drivetrain).withHeadingDegrees(270), new
    // LowerArm(logger, intake),
    // new ParallelRaceGroup(new IntakeIn(logger, intake),
    // new SequentialCommandGroup(
    // new TimedAutonMoveBackward(logger, _config,
    // drivetrain).withTime(1.5).withVelocity(1),
    // new TimedMoveHeading(logger, config,
    // drivetrain).withTime(1.5).withVelocity(1).withHeading(0))),
    // new TurnToHeading(logger, config, drivetrain).withHeadingDegrees(0),
    // new TrackVisionTarget(logger, config, drivetrain, new Limelight(config)),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3),
    // new ShootOneBallCommand(logger, shooter).withTimeout(3), new
    // ShooterShutdownCommand(logger, shooter));
    m_disabledCommand = new ShooterShutdownCommand(logger, shooter);
  }

  public Command getTeleopCommand() {
    return m_teleopCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public SequentialCommandGroup getAutonomousCommand() {

    SendableChooser<SequentialCommandGroup> m_chooser = new SendableChooser<SequentialCommandGroup>();

    m_chooser.setDefaultOption("ThreeDefault", m_ThreeBallAuto);
    m_chooser.addObject("LowScore", m_LowScore);

    // m_ThreeBallAuto Is the Three ball auto.
    // If it is different, It is UNTESTED
    return m_ThreeBallAuto;
  }

  public Command whenButtonAPressed() {
    // add a command that should be run when the controller A button is pressed
    return null;
  }

}
