/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.Injector;

/**
 * Add your docs here.
 */
public class GuiceDependencyContainer implements IDependencyContainer {
    private Injector _container;

    public GuiceDependencyContainer(Injector container) {
        _container = container;
    }

    @Override
    public <T extends Object> T resolve(Class<T> type) {
        return _container.getInstance(type);
    }
}
