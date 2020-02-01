/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Log to the standard out
 */
public class ShuffleboardLogger extends LoggerBase {
    private boolean _isConfigured = false;
    private NetworkTable _tableRoot;
    private NetworkTableEntry _timeEntry;
    private NetworkTableEntry _levelEntry;
    private NetworkTableEntry _componentTypeEntry;
    private NetworkTableEntry _componentNameEntry;

    private NetworkTableEntry _messageEntry;

    private void configureLogging() {
        if (!_isConfigured) {
            NetworkTableInstance networkTables = NetworkTableInstance.getDefault();
            String key = "logging/" + this.getComponentType() + "/" + this.getComponentName();
            _tableRoot = networkTables.getTable(key);
            _timeEntry = _tableRoot.getEntry("timestamp");
            _levelEntry = networkTables.getTable(key).getEntry("level");
            // _componentTypeEntry = inst.getTable(key).getEntry("component");
            // _componentNameEntry = inst.getTable(key).getEntry("componentName");
            _messageEntry = networkTables.getTable(key).getEntry("message");
            _isConfigured = true;
        }
    }

    @Override
    public void log(LogLevel level, Object... message) {
        this.configureLogging();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
        _timeEntry.setString(dateFormat.format(new Date()));
        _levelEntry.setString(level.toString());
        // _componentTypeEntry.setString(this.getComponentType());
        // _componentNameEntry.setString(this.getComponentName());
        String msg = this.convertToString(message);
        _messageEntry.setString(msg);
    }

    @Override
    public void monitor(Object... keyValuesToMonitor) throws Exception {
        // validate we have an even number of values
        if (keyValuesToMonitor.length % 2 != 0) {
            throw new Exception("keyValuesToMonitor must include a key and value.");
        }
        NetworkTable table = _tableRoot.getSubTable("monitoring");
        for (int i = 0; i < keyValuesToMonitor.length; i += 2) {
            table.getEntry(keyValuesToMonitor[i].toString()).setValue(keyValuesToMonitor[i + 1]);
        }
    }
}
