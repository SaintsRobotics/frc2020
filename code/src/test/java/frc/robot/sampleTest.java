/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.robot.commands.ExampleCommand;
import frc.robot.common.ILogger;
import frc.robot.common.NullLogger;
import frc.robot.common.ShuffleboardLogger;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class sampleTest {

    @Test
    public void validateCommandTesting() {
        ExampleCommand cmd = new ExampleCommand(new TraceableMockSubsystem(new NullLogger()));
        cmd.execute();

        assertEquals(1, cmd.getRequirements().size());
    }
}
