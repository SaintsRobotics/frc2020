/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

/**
 * Log to the standard out
 */
public class ConsoleLogger extends LoggerBase {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public void log(LogLevel level, Object... messages) {
        String color = "";
        switch (level) {
        case CRITICAL:
        case ERROR:
            color = ANSI_RED;
            break;
        case WARNING:
            color = ANSI_YELLOW;
            break;
        case INFORMATION:
            color = ANSI_WHITE;
            break;
        default:
            color = ANSI_RESET;
        }
        String msg = this.convertToString(messages);
        String output = color + level.toString() + ": " + "(" + this.getComponentType() + ":" + this.getComponentName()
                + ") " + msg;
        System.out.println(output);
    }

}