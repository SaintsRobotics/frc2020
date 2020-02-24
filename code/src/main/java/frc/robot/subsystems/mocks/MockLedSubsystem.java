/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.common.ILedSubsystem;
import frc.robot.common.ILogger;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockLedSubsystem extends TraceableMockSubsystem implements ILedSubsystem {

    public MockLedSubsystem(ILogger logger) {
        super(logger);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setColors(Color background, Color foreground) {
        // TODO Auto-generated method stub

    }

}