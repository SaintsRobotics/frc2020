/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.unit.subsystems.drivetrain;

import org.junit.*;

import frc.robot.RobotContainer;
import frc.robot.mocks.MockRobot;

/**
 * Add your docs here.
 */
public class Feature_drivetrain_supports_trajectory_tracking {
    private RobotContainer _robot;
    private MockRobot _mockRobot;

    @Before
    public void beforeEachTest() {
        _mockRobot = new MockRobot();
        _robot = _mockRobot.getRobot();
    }

    @After
    public void afterEachTeast() {
        _mockRobot.close();
    }

    @Test
    @Ignore
    public void Scenario_travel_from_point_A_to_point_B() {

    }

    @Test
    @Ignore
    public void Scenario_travel_from_multiple_points_A_through_D() {

    }

    @Test
    @Ignore
    public void Scenario_travel_from_point_A_to_point_B_changing_heading() {

    }

    @Test
    @Ignore
    public void Scenario_travel_from_multiple_points_A_through_D_changing_heading() {

    }
}
