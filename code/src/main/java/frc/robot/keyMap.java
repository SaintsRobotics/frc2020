package frc.robot;

import java.util.*;

import frc.robot.commands.navcommands.*;
import frc.robot.common.TraceableCommand;

public class keyMap {
    // should change TraceableCommand to ParseableCommand
    public static final HashMap<String, TraceableCommand> map = new HashMap<String, TraceableCommand>();

    public keyMap() {
        // put commands in map
        map.put("mo", new MoveCommand(null, null, null));
        map.put("la", new LowerArm(null, null));
        map.put("ii", new IntakeIn(null, null));
        map.put("io", new IntakeOut(null, null));
    }
}