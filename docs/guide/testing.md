# Testing

The project uses three different levels of testing. Each level serves a different purpose and may not be able to be run directly from the IDE.

## Building

When every you build your code `ALL` tests will be run including `unit` and `simulation`.

## Unit

This is the most used category and refers to `unit tests` which are tests that can be run without the need for the robot or a simulator. These tests make use of `mock` or `fake` versions of the subsystems. The `mock` subsystems try to simulate the values you'd expect from the real subsystems. These tests can be run easily directly from the VSCode IDE. This is the recommend type of test to create for most purposes.

## Simulation

These tests make use of the FRC WPILib simulator. They allow the ability to test the robot code without having to mock any of the real subsystems (assuming vendor support). However, these tests can't be run directly from the VSCode IDE. They require the simulated HAL (Hardware Abstraction Layer) be created (same as running the FRC Simulator) which isn't currently supported in the IDE. As such these tests can only be run via the gradle build command. Its recommended that this style of test be used sparingly and only when a unit test can't be created.

# Testing Style
There are a number of ways to write tests and a number of different styles. The style of tests adopted for this project follows a scenario based approach. This is likely different to what you may have previously seen used on other projects. This style attempts to make the tests clearer and focused on what the code should do rather than how it does it.

Each test is made up of a Feature and a number of Scenarios. The Feature is a short description of what you want the code to do from a users perspective not a technical one. In some cases the 'user' maybe a system. Then each feature has one or more Scenarios. A Scenario is a user or system action that you want to validate which is related to the Feature.

## Example

```java
public class Feature_Robot_configured_for_competition {

    @Test
    public void Scenario_autonomous_command_configured() {
        RobotHost robotHost = new RobotHost();
        Robot robot = (Robot) robotHost.getRobot();
        Command cmd = robot.getAutonomousCommand();
        assertEquals("ExampleCommand", cmd.getName());
        robotHost.close();
    }
}
```

This shows a simple example of a `simulation` test which focuses on making sure the robot is configured correctly for the competition. So the `Feature` here is that the robot is competition ready. The `Scenario` validates that the robot has its `autonomous` command correctly set for the competition.

The idea is that when you read the output of these tests that they make sense and provide you with confidence that you've tested everything you need.
 