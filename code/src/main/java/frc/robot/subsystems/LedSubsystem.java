/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.google.inject.Inject;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotConfig;
import frc.robot.common.ILedSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableSubsystem;

/**
 * Add your docs here.
 */
public class LedSubsystem extends TraceableSubsystem implements ILedSubsystem {

    private static final String BACKGROUND_COMMAND = "b";
    private static final String FOREGROUND_COMMAND = "f";

    private SerialPort serialPort;

    @Inject
    public LedSubsystem(ILogger logger) {
        super(logger);

        System.out.println("creating serialport");
        this.serialPort = new SerialPort(115200, SerialPort.Port.kUSB);
        System.out.println("created subsystem");
    }

    @Override
    public void setColors(Color background, Color foreground) {
        System.out.println("setting color");
        this.sendCommand(BACKGROUND_COMMAND, background.red, background.green, background.blue);
        this.sendCommand(FOREGROUND_COMMAND, foreground.red, foreground.green, foreground.blue);
    }

    private void sendCommand(String command, Object... args) {
        String[] stringArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = args[i].toString();
        }
        String commandToSend = command + String.join(",", stringArgs) + "\n";
        System.out.println(commandToSend);

        serialPort.writeString(commandToSend);
        // String returnString = serialPort.readString();
        // if (!returnString.equals(commandToSend)) {
        // DriverStation.reportWarning(
        // "The leds were set to " + commandToSend + ", but the controller reported " +
        // returnString, false);

        // }
        // DriverStation.reportWarning("The leds were set to " + commandToSend, false);
    }

    @Override
    public void periodic() {
        super.periodic();
        // System.out.println("LED Subsystem periodic");
        if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
            this.sendCommand("t", 2);
        } else if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
            this.sendCommand("t", 3);
        } else {
            this.sendCommand("t", 1);
        }
    }

}
