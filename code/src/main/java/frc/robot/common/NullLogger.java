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
public class NullLogger implements ILogger {
    private String _componentType;
    private String _componentName;

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

    }

    @Override
    public void setComponentName(String name) {
        _componentName = name;
    }

    @Override
    public void verbose(String message) {
        // No-Op
    }

    @Override
    public void error(String message) {
        // No-Op
    }
}
