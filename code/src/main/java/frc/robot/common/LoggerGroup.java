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
public class LoggerGroup implements ILogger {
    private List<ILogger> _loggers;
    private String _componentType;
    private String _componentName;

    public LoggerGroup(ILogger... loggers) {
        _loggers = List.of(loggers);
    }

    @Override
    public String getComponentType() {
        return _componentType;
    }

    @Override
    public String getComponentName() {
        return _componentName;
    }

    @Override
    public void setComponentType(String name) {
        _componentType = name;
        // now also apply to the collection of actual loggers
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).setComponentType(name);
        }
    }

    @Override
    public void setComponentName(String name) {
        _componentName = name;
        // now also apply to the collection of actual loggers
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).setComponentName(name);
        }
    }

    @Override
    public void verbose(String message) {
        for (int i = 0; i < _loggers.size(); i++) {
            _loggers.get(i).verbose(message);
        }
    }
}
