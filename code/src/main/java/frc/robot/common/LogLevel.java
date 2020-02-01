/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

/**
 * Add your docs here.
 */
public enum LogLevel {
    /**
     * Logs that contain the most detailed messages. These messages may contain
     * sensitive application data. These messages are disabled by default and should
     * never be enabled in a production environment.
     */
    TRACE,
    /**
     * Logs that are used for interactive investigation during development. These
     * logs should primarily contain information useful for debugging and have no
     * long-term value.
     */
    DEBUG,
    /**
     * Logs that track the general flow of the application. These logs should have
     * long-term value.
     */
    INFORMATION,
    /**
     * 
     * Logs that highlight an abnormal or unexpected event in the application flow,
     * but do not otherwise cause the application execution to stop.
     */
    WARNING,
    /**
     * Logs that highlight when the current flow of execution is stopped due to a
     * failure. These should indicate a failure in the current activity, not an
     * application-wide failure.
     */
    ERROR,
    /**
     * 
     * Logs that describe an unrecoverable application or system crash, or a
     * catastrophic failure that requires immediate attention.
     */
    CRITICAL
}
