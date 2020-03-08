/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.common.ILedSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

/**
 * Add your docs here.
 */
public class LedSubsystem extends TraceableSubsystem implements ILedSubsystem {

    private static final String BACKGROUND_COMMAND = "b";
    private static final String FOREGROUND_COMMAND = "f";
    private static final String PRESET_COMMAND = "t";

    private SerialPort serialPort;

    @Inject
    public LedSubsystem(ILogger logger) {
        super(logger);

        this.serialPort = new SerialPort(115200, SerialPort.Port.kUSB);
    }

    @Override
    public void setColors(Color background, Color foreground) {
        this.sendCommand(BACKGROUND_COMMAND, background.red, background.green, background.blue);
        this.sendCommand(FOREGROUND_COMMAND, foreground.red, foreground.green, foreground.blue);
    }

    @Override
    public void setPreset(Alliance alliance) {
        switch (alliance) {
        case Blue:
            this.sendCommand(PRESET_COMMAND, 2);
            break;
        case Red:
            this.sendCommand(PRESET_COMMAND, 3);
            break;
        case Invalid:
            this.sendCommand(PRESET_COMMAND, 1);
        }
    }

    private void sendCommand(String command, Object... args) {
        String[] stringArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = args[i].toString();
        }
        String commandToSend = command + String.join(",", stringArgs) + "\n";

        serialPort.writeString(commandToSend);
        String returnString = serialPort.readString();
        if (!returnString.equals(commandToSend)) {
            DriverStation.reportWarning(
                    "The leds were set to " + commandToSend + ", but the controller reported " + returnString, false);

        }

    }

}
