/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.Inject;
import com.google.inject.Provider;

import frc.robot.commands.navcommands.DrivetrainControllerCommand;
import frc.robot.commands.navcommands.OdometryCommand;
import frc.robot.commands.navcommands.turnToHeading;
import frc.robot.commands.IntakeControllerCommand;

import frc.robot.commands.ShooterCommand;

import frc.robot.common.CompetitionRobot;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This has the core logic for the Robot. This class must not include any
 * classes that have a dependency on hardware as this will break the ability to
 * unit test.
 */
public class RobotContainer extends CompetitionRobot {
  // private final Provider<DrivetrainControllerCommand> _autonomousCommand;
  private OdometryCommand testing;
  private Pose2d[] path = new Pose2d[1];

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  @Inject

  private RobotContainer(final ILogger logger, IDrivetrainSubsystem drivetrain,
      DrivetrainControllerCommand driveCommand, IIntakeSubsystem intake, IntakeControllerCommand intakeCommand,
      IShooterSubsystem shooterSubsystem, ShooterCommand shooterCommand, RobotConfig config) {
    super(logger);

    intake.setDefaultCommand(intakeCommand);

    // shooterSubsystem.setDefaultCommand(shooterCommand);

    drivetrain.setDefaultCommand(driveCommand);

    path[0] = new Pose2d(-1, 0, new Rotation2d());
    // path[1] = new Pose2d(-1, 1, new Rotation2d());
    // path[2] = new Pose2d(-1, 1, new Rotation2d(Math.PI));
    testing = new OdometryCommand(logger, drivetrain, path, config);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // // An ExampleCommand will run in autonomous
    turnToHeading cmd;
    return  cmd.withHeading(90)
    return testing;
  }

  public Command whenButtonAPressed() {
    // add a command that should be run when the controller A button is pressed
    return null;
  }

}
