/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.Inject;

import frc.robot.commands.ClimbControllerCommand;
import frc.robot.commands.DriveArmCommand;
import frc.robot.commands.DrivetrainControllerCommand;

import frc.robot.common.CompetitionRobot;
import frc.robot.common.IClimbSubsystem;
import frc.robot.common.IDrivetrainSubsystem;
import frc.robot.common.IIntakeSubsystem;
import frc.robot.common.ILogger;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This has the core logic for the Robot. This class must not include any
 * classes that have a dependency on hardware as this will break the ability to
 * unit test.
 */
public class RobotContainer extends CompetitionRobot {

  private Command m_teleopCommand;
  // private final Provider<DrivetrainControllerCommand> _autonomousCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  @Inject
  private RobotContainer(final ILogger logger, IDrivetrainSubsystem drivetrain,
      DrivetrainControllerCommand driveCommand, IIntakeSubsystem intake, DriveArmCommand driveArmCommand,
      IClimbSubsystem climb, ClimbControllerCommand climbCommand) {
    super(logger);

    drivetrain.setDefaultCommand(driveCommand);
    intake.setDefaultCommand(driveArmCommand);
    climb.setDefaultCommand(climbCommand);

  }

  public Command getTeleopCommand() {
    return m_teleopCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // // An ExampleCommand will run in autonomous
  // return _autonomousCommand.get();
  // }

  public Command whenButtonAPressed() {
    // add a command that should be run when the controller A button is pressed
    return null;
  }

}
