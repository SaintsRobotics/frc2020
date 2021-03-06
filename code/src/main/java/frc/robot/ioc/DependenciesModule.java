/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.navcommands.GoToPosition;
import frc.robot.commands.navcommands.mocks.MockGotoPosition;
import frc.robot.common.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.mocks.MatchSimDrivetrain;
import frc.robot.subsystems.mocks.MockCommandDrivetrain;
import frc.robot.subsystems.mocks.MockDrivetrain;
import frc.robot.subsystems.mocks.MockIntake;
import frc.robot.subsystems.mocks.MockLedSubsystem;
import frc.robot.subsystems.mocks.MockShooter;

/**
 * Add your docs here.
 */
public class DependenciesModule extends AbstractModule {
    protected void configure() {
        // create logger for injecting

        this.bind(ILogger.class).toProvider(LoggerProvider.class);
        this.bind(Location.class).asEagerSingleton();
        // Due to some subsystems not being compatible with the HAL Sims we need to use
        // mocks instead
        if (RobotBase.isReal()) {
            this.bind(IShooterSubsystem.class).to(ShooterSubsystem.class).in(Singleton.class);
            this.bind(IDrivetrainSubsystem.class).to(SwerveDrivetrain.class).in(Singleton.class);
            this.bind(IClimbSubsystem.class).to(ClimbSubsystem.class).in(Singleton.class);

            this.bind(IIntakeSubsystem.class).to(Intake.class).in(Singleton.class);

            this.bind(ILedSubsystem.class).to(LedSubsystem.class).in(Singleton.class);

        } else {
            this.bind(IShooterSubsystem.class).to(MockShooter.class).in(Singleton.class);
            this.bind(IDrivetrainSubsystem.class).to(MockDrivetrain.class).in(Singleton.class);
            this.bind(IIntakeSubsystem.class).to(MockIntake.class).in(Singleton.class);
            this.bind(MockCommandDrivetrain.class).to(MatchSimDrivetrain.class);

            // Mock out the Navigation Commands
            this.bind(GoToPosition.class).to(MockGotoPosition.class);

            this.bind(ILedSubsystem.class).to(MockLedSubsystem.class).in(Singleton.class);
        }
    }
}
