// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
// /* Open Source Software - may be modified and shared by FRC teams. The code
// */
// /* must be accompanied by the FIRST BSD license file in the root directory of
// */
// /* the project. */
// /*----------------------------------------------------------------------------*/

// package frc.robot.unit.robot;

// import static org.junit.Assert.assertEquals;

// import org.junit.Test;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.Robot;
// import frc.robot.commands.DrivetrainControllerCommand;
// import frc.robot.mocks.FakeRobot;
// import frc.robot.mocks.MockRobot;

// TODO: Need to work out how to simulate a competition. Can currently run the
// match but can't work out how to enable the bot

// /**
// * Add your docs here.
// */
// public class Feature_FakeRobot_simulates_competition {
// @Test
// public void Scenario_robot_starts_in_teleOp_mode() {
// final MockRobot robot = new MockRobot();
// // robot.startCompetition(100);

// // assertEquals(FakeRobot.Mode.kTeleop, robot.getCurrentMode());
// }

// @Test
// public void Scenario_robot_executes_scheduled_command() {
// final FakeRobot robot = new FakeRobot();
// robot.startCompetition(100);

// final Command cmd = robot.createInstance(DrivetrainControllerCommand.class);
// cmd.schedule();

// assertEquals(FakeRobot.Mode.kTeleop, robot.getCurrentMode());
// }

// @Test
// public void Test_Scheduler() {
// MockRobot robot = new MockRobot();
// Runnable runnable = new Runnable() {
// @Override
// public void run() {
// robot.startRobot();
// }
// };
// Thread thread = new Thread(runnable);
// thread.start();
// // robot.startRobot();
// // final CommandScheduler scheduler = CommandScheduler.getInstance(); //
// // constructor is private?
// // final Command cmd =
// // robot.createInstance(DrivetrainControllerCommand.class).runWhenDisabled();
// // cmd.schedule();
// // scheduler.run();
// // assertEquals(true, scheduler.isScheduled(cmd));
// try {
// Thread.sleep(1000 * 20);
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// robot.isEnabled();
// robot.close();
// }

// }
