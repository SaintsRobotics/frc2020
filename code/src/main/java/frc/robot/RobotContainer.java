/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.SwerveWheel;
import frc.robot.util.AbsoluteEncoder;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final double testBotX = 0.32385;
  private final double testBotY = 0.2794;

  // The robot's subsystems and commands are defined here...
  private final CANSparkMax testBotFrontLeftDrive = new CANSparkMax(2, MotorType.kBrushless);
  private final SpeedController testBotFrontLeftTurn = new Talon(5);
  private final CANEncoder testBotFrontLeftDrivEncoder = testBotFrontLeftDrive.getEncoder();
  public static AbsoluteEncoder testBotLeftFrontTurnEncoder = new AbsoluteEncoder(1, true);

  private final SwerveWheel testBotFrontLeft = new SwerveWheel(testBotFrontLeftDrive, testBotFrontLeftTurn,
      testBotFrontLeftDrivEncoder, testBotLeftFrontTurnEncoder, -testBotX, testBotY);

  private final CANSparkMax testBotFrontRightDrive = new CANSparkMax(1, MotorType.kBrushless);
  private final SpeedController testBotFrontRightTurn = new Talon(4);
  private final CANEncoder testBotFrontRightCanEncoder = testBotFrontRightDrive.getEncoder();
  public static AbsoluteEncoder testBotRightFrontTurnEncoder = new AbsoluteEncoder(3, true);

  private final SwerveWheel testBotFrontRight = new SwerveWheel(testBotFrontRightDrive, testBotFrontRightTurn,
      testBotFrontRightCanEncoder, testBotRightFrontTurnEncoder, testBotX, testBotY);

  private final CANSparkMax testBotBackLeftDrive = new CANSparkMax(3, MotorType.kBrushless);
  private final SpeedController testBotBackLeftTurn = new Talon(6);
  private final CANEncoder testBotBackLeftEncoder = testBotBackLeftDrive.getEncoder();
  public static AbsoluteEncoder testBotBackLeftTurnEncoder = new AbsoluteEncoder(2, true);
  private final SwerveWheel testBotBackLeft = new SwerveWheel(testBotBackLeftDrive, testBotBackLeftTurn,
      testBotBackLeftEncoder, testBotBackLeftTurnEncoder, -testBotX, -testBotY);

  private final CANSparkMax testBotBackRightDrive = new CANSparkMax(4, MotorType.kBrushless);
  private final SpeedController testBotBackRightTurn = new Talon(7);
  private final CANEncoder testBotBackRightDrivEncoder = testBotBackRightDrive.getEncoder();
  public static AbsoluteEncoder testBotBackRightEncoder = new AbsoluteEncoder(3, true);
  private final SwerveWheel testBotBackRight = new SwerveWheel(testBotBackRightDrive, testBotBackRightTurn,
      testBotBackRightDrivEncoder, testBotBackRightEncoder, testBotX, -testBotY);

  private final SwerveSubsystem testBotDrive = new SwerveSubsystem(testBotFrontLeft, testBotFrontRight, testBotBackLeft,
      testBotBackRight);

  private final double compBotX = 0.32385;
  private final double compBotY = 0.2794;

  // The robot's subsystems and commands are defined here...
  private final CANSparkMax compBotFrontLeftDrive = new CANSparkMax(0, MotorType.kBrushless);
  private final CANSparkMax compBotFrontLeftTurn = new CANSparkMax(1, MotorType.kBrushless);
  private final CANEncoder compBotFrontLeftDriveEncoder = compBotFrontLeftDrive.getEncoder();
  public static AbsoluteEncoder compBotFrontLeftTurnEncoder = new AbsoluteEncoder(0, true);
  private final SwerveWheel compBotFrontLeft = new SwerveWheel(compBotFrontLeftDrive, compBotFrontLeftTurn,
      compBotFrontLeftDriveEncoder, compBotFrontLeftTurnEncoder, -compBotX, compBotY);

  private final CANSparkMax compBotFrontRightDrive = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax compBotFrontRightTurn = new CANSparkMax(3, MotorType.kBrushless);
  private final CANEncoder compBotFrontRightDriveEncoder = compBotFrontRightDrive.getEncoder();
  public static AbsoluteEncoder compBotFrontRightTurnEncoder = new AbsoluteEncoder(1, true);
  private final SwerveWheel compBotFrontRight = new SwerveWheel(compBotFrontRightDrive, compBotFrontRightTurn,
      compBotFrontRightDriveEncoder, compBotFrontRightTurnEncoder, compBotX, compBotY);

  private final CANSparkMax compBotBackLeftDrive = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax compBotBackLeftTurn = new CANSparkMax(5, MotorType.kBrushless);
  private final CANEncoder compBotBackLeftDriveEncoder = compBotBackLeftDrive.getEncoder();
  public static AbsoluteEncoder compBotBackLeftTurnEncoder = new AbsoluteEncoder(2, true);
  private final SwerveWheel compBotBackLeft = new SwerveWheel(compBotBackLeftDrive, compBotBackLeftTurn,
      compBotBackLeftDriveEncoder, compBotBackLeftTurnEncoder, -compBotX, -compBotY);

  private final CANSparkMax compBotBackRightDrive = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax compBotBackRightTurn = new CANSparkMax(7, MotorType.kBrushless);
  private final CANEncoder compBotBackRightDriveEncoder = compBotBackRightDrive.getEncoder();
  public static AbsoluteEncoder compBotBackRightTurnEncoder = new AbsoluteEncoder(3, true);
  private final SwerveWheel compBotBackRight = new SwerveWheel(compBotBackRightDrive, compBotBackRightTurn,
      compBotBackRightDriveEncoder, compBotBackRightTurnEncoder, compBotX, -compBotY);

  private final SwerveSubsystem compBotDrive = new SwerveSubsystem(compBotFrontLeft, compBotFrontRight, compBotBackLeft,
      compBotBackRight);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

}
