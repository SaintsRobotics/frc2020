/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sim;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Used to communicate with the Match Simulator Shuffleboard widget
 */
public class MatchSimulator {
    private String _tableRoot = "MatchSimulator/";
    private NetworkTable _locationTable;

    public MatchSimulator() {
        NetworkTableInstance networkTables = NetworkTableInstance.getDefault();
        _locationTable = networkTables.getTable(_tableRoot + "Location");
        // _locationTable = networkTables.getTable(_tableRoot + "Location");

        // set defaults so that they are visible in the NetworkTables without
        // starting the robot

        this.updateLocation(0, 0, 0);
        this.updateStartingLocation(0, 0, 0);
    }

    /**
     * Updates the simulator with the current x, y and heading (rotation)
     * 
     * @param x
     * @param y
     * @param heading
     */
    public void updateLocation(double x, double y, double heading) {
        _locationTable.getEntry("x").forceSetDouble(x);
        _locationTable.getEntry("y").forceSetDouble(y);
        _locationTable.getEntry("heading").forceSetDouble(heading);
    }

    public void updateStartingLocation(double x, double y, double heading) {
        _locationTable.getEntry("start-x").forceSetDouble(x);
        _locationTable.getEntry("start-y").forceSetDouble(x);
        _locationTable.getEntry("start-heading").forceSetDouble(heading);
    }
}
