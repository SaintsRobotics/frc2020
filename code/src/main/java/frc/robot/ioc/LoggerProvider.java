/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ioc;

import com.google.inject.Provider;
import frc.robot.common.ILogger;
import frc.robot.common.*;

/**
 * Add your docs here.
 */
public class LoggerProvider implements Provider<ILogger> {

    @Override
    public ILogger get() {
        return new LoggerGroup(new ShuffleboardLogger());
    }

}