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
public class ConsoleLogger extends NullLogger {
    @Override
    public void verbose(String message) {
        System.out.println("(" + this.getComponentType() + ":" + this.getComponentName() + ") " + message);
    }

    @Override
    public void error(String message) {
        System.err.println("(" + this.getComponentType() + ":" + this.getComponentName() + ") " + message);
    }
}