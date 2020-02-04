/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.Inject;
import com.google.inject.Provider;

import frc.robot.commands.DrivetrainControllerCommand;
import frc.robot.common.CompetitionRobot;
import frc.robot.common.ILogger;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This has the core logic for the Robot. This class must not include any
 * classes that have a dependency on hardware as this will break the ability to
 * unit test.
 */
public class RobotContainer extends CompetitionRobot {
  private final Provider<DrivetrainControllerCommand> _autonomousCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  @Inject
  public RobotContainer(final ILogger logger, final Provider<DrivetrainControllerCommand> autonomousCommand) {
    super(logger);
    _autonomousCommand = autonomousCommand;

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return _autonomousCommand.get();
  }

  public Command whenButtonAPressed() {
    // add a command that should be run when the controller A button is pressed
    return null;
  }

}
