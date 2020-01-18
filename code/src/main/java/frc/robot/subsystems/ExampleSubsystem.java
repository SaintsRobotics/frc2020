/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableSubsystem;

public class ExampleSubsystem extends TraceableSubsystem {
  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem(final ILogger logger) {
    super(logger);
    final Gyro x;
  }

  @Override
  public void periodic() {
    super.periodic();
    // This method will be called once per scheduler run
  }
}
