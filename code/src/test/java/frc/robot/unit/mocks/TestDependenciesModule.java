/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.unit.mocks;

import com.google.inject.AbstractModule;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.common.*;
import frc.robot.unit.mocks.subsystems.MockDrivetrain;

/**
 * Add your docs here.
 */
public class TestDependenciesModule extends AbstractModule {
    protected void configure() {
        // create logger for injecting
        ILogger logger = new LoggerGroup(new ConsoleLogger());

        this.bind(ILogger.class).toInstance(logger);
        this.bind(IDrivetrainSubsystem.class).to(MockDrivetrain.class);
    }
}
