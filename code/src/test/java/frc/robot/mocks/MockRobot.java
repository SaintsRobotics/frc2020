/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.mocks;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.Robot;
import frc.robot.TestDependenciesModule;

/**
 * A Mock version of the Robot class. This is essentially the same with a few
 * modifications to support testing
 */
public class MockRobot extends Robot {

    public MockRobot() {
        super(new TestDependenciesModule());
    }

    public void startRobot() {
        RobotBase.startRobot(MockRobot::new);
    }

    public void startCompetition() {
        super.startCompetition();

    }

    // public void startCompetition(int matchLengthInMilliseconds) {
    // this.startCompetition(matchLengthInMilliseconds, Mode.kTeleop, 20);
    // }

    // public void startCompetition(int matchLengthInMilliseconds, Mode mode) {
    // this.startCompetition(matchLengthInMilliseconds, mode, 20);
    // }

}
