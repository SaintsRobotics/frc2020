// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.unit.robot;

// import static org.junit.Assert.assertEquals;

// import org.junit.Test;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.mocks.FakeRobot;

// /**
//  * Add your docs here.
//  */
// public class Feature_Robot_configured_for_competition {

    @Test
    public void Scenario_robot_is_drivable() {
        RobotContainer robot = new FakeRobot().getRobot();
        Command cmd = robot.getAutonomousCommand();
        assertEquals("DrivetrainControllerCommand", cmd.getName());
    }
}
