/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.unit.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotHost;
import frc.robot.commands.DrivetrainControllerCommand;
import frc.robot.unit.mocks.FakeRobot;

/**
 * Add your docs here.
 */
public class Feature_FakeRobot_simulates_competition {
    @Test
    public void Scenario_robot_starts_in_teleOp_mode() {
        final FakeRobot robot = new FakeRobot();
        robot.startCompetition(100);

        assertEquals(FakeRobot.Mode.kTeleop, robot.getCurrentMode());
    }

    @Test
    public void Scenario_robot_executes_scheduled_command() {
        final FakeRobot robot = new FakeRobot();
        robot.startCompetition(100);

        final Command cmd = robot.createInstance(DrivetrainControllerCommand.class);
        cmd.schedule();

        assertEquals(FakeRobot.Mode.kTeleop, robot.getCurrentMode());
    }

    @Test
    public void Test_Scheduler() {
        // final RobotHost robot = new FakeRobot();
        // robot.startCompetition(30000);
        RobotHost robot = new RobotHost();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                robot.startCompetition();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        final CommandScheduler scheduler = CommandScheduler.getInstance(); // constructor is private?
        final Command cmd = robot.createInstance(DrivetrainControllerCommand.class).runWhenDisabled();
        cmd.schedule();
        // scheduler.run();
        // assertEquals(true, scheduler.isScheduled(cmd));
        robot.close();
    }

}
