
// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
// /* Open Source Software - may be modified and shared by FRC teams. The code
// */
// /* must be accompanied by the FIRST BSD license file in the root directory of
// */
// /* the project. */
// /*----------------------------------------------------------------------------*/
// package frc.robot.unit.subsystems.drivetrain;

// import org.junit.runner.RunWith;

// import static org.junit.Assert.assertEquals;

// import com.tngtech.java.junit.dataprovider.DataProvider;
// import com.tngtech.java.junit.dataprovider.DataProviderRunner;
// import com.tngtech.java.junit.dataprovider.UseDataProvider;

// import org.junit.Before;
// import org.junit.Test;

// import frc.robot.common.ConsoleLogger;
// import frc.robot.common.ILogger;
// import frc.robot.common.Location;
// import frc.robot.mocks.MockMatchSimulator;
// import frc.robot.subsystems.mocks.MockCommandDrivetrain;
// import frc.robot.unit.Pose2dEx;

// /**
// * Add your docs here.@
// */

// @RunWith(DataProviderRunner.class)
// public class Feature_move_robot_using_high_level_commands {
// Location _location;
// ILogger _logger;
// MockCommandDrivetrain _drivetrain;

// @Before
// public void beforeEachTest() {
// _logger = new ConsoleLogger();
// _location = new Location(_logger, new MockMatchSimulator());
// _drivetrain = new MockCommandDrivetrain(_logger, _location);
// }

// @DataProvider
// public static Object[][] rotationData() {
// // initialHeading, rotation, finalHeading
// return new Object[][] { { 10, 50, 60 }, { 350, 20, 10 }, { 40, -60, 340 }, {
// 180, -40, 140 } };
// }

// @Test
// @UseDataProvider("rotationData")
// public void Scenario_rotate_robot_by_x_degrees(final double initialHeading,
// final double rotation,
// final double finalHeading) {

// _location.updateHeading(initialHeading);
// _drivetrain.rotate(rotation);
// assertEquals(finalHeading, _location.getHeading(), 0.01);
// }
// ///
// ---------------------------------------------------------------------------------------

// @DataProvider
// public static Object[][] moveForwardData() {
// // heading, initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] {
// //
// { 0, new Pose2dEx(0, 0), 1.5, new Pose2dEx(0, 1.5) },
// { 0, new Pose2dEx(2.4, 4.9), 2.5, new Pose2dEx(2.4, 7.4) },
// // with rotation
// { 45, new Pose2dEx(0, 0), 8.6, new Pose2dEx(6.08111, 6.08111) },
// { 90, new Pose2dEx(2, 1), 2, new Pose2dEx(4, 1) },
// //
// { 180, new Pose2dEx(2, 2), 2, new Pose2dEx(2, 0) },
// //
// { 270, new Pose2dEx(2, 2), 2, new Pose2dEx(0, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveForwardData")
// public void Scenario_move_forward(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveForward(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// @DataProvider
// public static Object[][] moveBackwardData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(3, 3), 1.5, new Pose2dEx(3, 1.5) },
// { 0, new Pose2dEx(2.4, 4.9), 2.5, new Pose2dEx(2.4, 2.4) },
// // with rotation
// { 45, new Pose2dEx(10, 10), 8.6, new Pose2dEx(3.91888, 3.91888) },
// { 90, new Pose2dEx(2, 1), 2, new Pose2dEx(0, 1) }, { 180, new Pose2dEx(2, 2),
// 2, new Pose2dEx(2, 4) },
// { 270, new Pose2dEx(2, 2), 2, new Pose2dEx(4, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveBackwardData")
// public void Scenario_move_backward(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveBackward(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveLeftData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(3, 3), 1.5, new Pose2dEx(1.5, 3) },
// { 90, new Pose2dEx(2.4, 4.9), 2.5, new Pose2dEx(2.4, 7.4) },
// { 135, new Pose2dEx(0, 0), 8.6, new Pose2dEx(6.08111, 6.08111) },
// { 180, new Pose2dEx(2, 1), 2, new Pose2dEx(4, 1) }, { 270, new Pose2dEx(2,
// 2), 2, new Pose2dEx(2, 0) },
// { 360, new Pose2dEx(2, 2), 2, new Pose2dEx(0, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveLeftData")
// public void Scenario_move_Left(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveLeft(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveRightData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(2, 2), 2, new Pose2dEx(4, 2) },
// { 90, new Pose2dEx(2, 2), 2, new Pose2dEx(2, 0) },
// { 315, new Pose2dEx(0, 0), 8.6, new Pose2dEx(6.08111, 6.08111) },
// { 180, new Pose2dEx(2, 2), 2, new Pose2dEx(0, 2) }, { 270, new Pose2dEx(2,
// 2), 2, new Pose2dEx(2, 4) },
// { 360, new Pose2dEx(2, 2), 2, new Pose2dEx(4, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveRightData")
// public void Scenario_move_Right(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveRight(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveNorthData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(2, 2), 2, new Pose2dEx(2, 4) },
// { 90, new Pose2dEx(2, 4), 2, new Pose2dEx(2, 6) },
// { 45, new Pose2dEx(0, 0), 8.6, new Pose2dEx(0, 8.6) },
// { 180, new Pose2dEx(7, 1), 2, new Pose2dEx(7, 3) }, { 270, new Pose2dEx(0,
// 3), 2, new Pose2dEx(0, 5) },
// { 360, new Pose2dEx(3, 6), 2, new Pose2dEx(3, 8) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveNorthData")
// public void Scenario_move_North(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveNorth(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveEastData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(2, 2), 2, new Pose2dEx(4, 2) },
// { 90, new Pose2dEx(2, 2), 3, new Pose2dEx(5, 2) },
// { 45, new Pose2dEx(0, 0), 8.6, new Pose2dEx(8.6, 0) },
// { 180, new Pose2dEx(8, 2), 4, new Pose2dEx(12, 2) },
// { 270, new Pose2dEx(2, 2), 10, new Pose2dEx(12, 2) },
// { 360, new Pose2dEx(2, 2), 6, new Pose2dEx(8, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveEastData")
// public void Scenario_move_East(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveEast(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveWestData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(2, 2), 2, new Pose2dEx(0, 2) },
// { 90, new Pose2dEx(2, 2), 2, new Pose2dEx(0, 2) },
// { 45, new Pose2dEx(10, 0), 8.6, new Pose2dEx(1.4, 0) },
// { 180, new Pose2dEx(2, 2), 1, new Pose2dEx(1, 2) }, { 270, new Pose2dEx(7,
// 2), 2, new Pose2dEx(5, 2) },
// { 360, new Pose2dEx(100, 2), 58, new Pose2dEx(42, 2) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveWestData")
// public void Scenario_move_West(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveWest(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] moveSouthData() {
// // initialPos(x,y), distance, finalPos(x,y)
// return new Object[][] { { 0, new Pose2dEx(2, 2), 2, new Pose2dEx(2, 0) },
// { 90, new Pose2dEx(2, 4), 2, new Pose2dEx(2, 2) },
// { 45, new Pose2dEx(17.2, 17.2), 8.6, new Pose2dEx(17.2, 8.6) },
// { 180, new Pose2dEx(7, 4), 2, new Pose2dEx(7, 2) },
// { 270, new Pose2dEx(123, 123), 123, new Pose2dEx(123, 0) },
// { 360, new Pose2dEx(3, 6), 2, new Pose2dEx(3, 4) },
// // invalid values wont move the robot
// { 0, new Pose2dEx(1, 1), -1, new Pose2dEx(1, 1) } };
// }

// @Test
// @UseDataProvider("moveSouthData")
// public void Scenario_move_South(final double heading, final Pose2dEx
// initialPose2dEx, final double distance,
// final Pose2dEx finalPosition) {

// _location.updatePosition(initialPose2dEx);
// _location.updateHeading(heading);
// _drivetrain.moveSouth(distance);

// assertEquals(finalPosition.getX(), _location.getX(), 0.001);
// assertEquals(finalPosition.getY(), _location.getY(), 0.001);
// }

// ///
// ---------------------------------------------------------------------------------------
// @DataProvider
// public static Object[][] followPathData() {
// // initialHeading, finalHeading, waypoints
// return new Object[][] {
// //
// { 0, 0, 0, new Pose2dEx[] { new Pose2dEx(2, 2), new Pose2dEx(1, 1) } },
// //
// { 0, 0, 0, new Pose2dEx[] { new Pose2dEx(2, 2), new Pose2dEx(10, 10) } } };
// }

// @Test
// @UseDataProvider("followPathData")
// public void Scenario_follow_Path(final double maxSpeed, final double
// initialHeading, final double finalHeading,
// final Pose2dEx[] waypoints) {

// _drivetrain.followPath(maxSpeed, initialHeading, waypoints);

// Pose2dEx finalPos = waypoints[waypoints.length - 1];

// assertEquals(finalPos.getX(), _location.getX(), 0.001);
// assertEquals(finalPos.getY(), _location.getY(), 0.001);
// }
// }
