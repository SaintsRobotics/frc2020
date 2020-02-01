/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import java.util.List;

/**
 * Provides ability to chain multiple loggers that will act as one
 */
public class LoggerGroup extends LoggerBase {
    private List<ILogger> _loggers;

    public LoggerGroup(ILogger... loggers) {
        _loggers = List.of(loggers);
    }

    @Override
    public void setComponentType(String name) {
        super.setComponentType(name);
        // now also apply to the collection of actual loggers
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).setComponentType(name);
        }
    }

    @Override
    public void setComponentName(String name) {
        super.setComponentName(name);
        // now also apply to the collection of actual loggers
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).setComponentName(name);
        }
    }

    @Override
    public void log(LogLevel level, Object... message) {
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).log(level, message);
        }
    }

    @Override
    public void monitor(Object... keyValuesToMonitor) throws Exception {
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).monitor(keyValuesToMonitor);
        }
    }
}
