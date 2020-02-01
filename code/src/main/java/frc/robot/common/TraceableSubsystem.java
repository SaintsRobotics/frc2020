/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Extends the default Subsystem with default tracing
public class TraceableSubsystem extends SubsystemBase {
  private ILogger _logger;

  /**
   * Creates a new TraceableSubsystem.
   */
  @Inject
  public TraceableSubsystem(ILogger logger) {
    _logger = logger;
    logger.setComponentType("Subsystem");
    logger.setComponentName(this.getName());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    _logger.debug("periodic");
    super.periodic();
  }

  protected ILogger getLogger() {
    return _logger;
  }
}
