package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.common.TraceableCommand;

public final class AutonParser {
    public SequentialCommandGroup parse(String keyArray[]) {
        SequentialCommandGroup group = new SequentialCommandGroup();
        for (String key : keyArray) {
            String ID = key.substring(0, 2); // assuming the identifier is the first 2 chars
            String params = key.substring(2);

            if (keyMap.map.containsKey(ID)) { // if ID is valid
                TraceableCommand command = keyMap.map.get(ID).withStringInput(params);
                /*
                 * Should change TraceableCommand to ParsableCommand. ParseableCommand will need
                 * to include a method withStringInput which will be implemented in each
                 * individual command. withStringInput will take in a String parameter and
                 * return type ParseableCommand.
                 */
                group.addCommands(command);
            }
        }
        return group;
    }
}
