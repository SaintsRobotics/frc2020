/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

/**
 * Add your docs here.
 */
public abstract class LoggerBase implements ILogger {
    private String _componentType;
    private String _componentName;

    @Override
    public String getComponentType() {
        return _componentType;
    }

    @Override
    public String getComponentName() {
        return _componentName;
    }

    @Override
    public void setComponentType(String name) {
        _componentType = name;

    }

    @Override
    public void setComponentName(String name) {
        _componentName = name;
    }

    @Override
    public abstract void log(LogLevel level, Object... message);

    @Override
    public void trace(Object... message) {
        this.log(LogLevel.TRACE, message);
    }

    @Override
    public void debug(Object... message) {
        this.log(LogLevel.DEBUG, message);
    }

    @Override
    public void information(Object... message) {
        this.log(LogLevel.INFORMATION, message);

    }

    @Override
    public void warning(Object... message) {
        this.log(LogLevel.WARNING, message);
    }

    @Override
    public void error(Object... message) {
        this.log(LogLevel.ERROR, message);
    }

    @Override
    public void error(Exception exception) {
        this.log(LogLevel.ERROR, exception.getMessage());
    }

    @Override
    public void critical(Object... message) {
        this.log(LogLevel.CRITICAL, message);
    }

    @Override
    public void monitor(Object... keyValuesToMonitor) throws Exception {
        // validate we have an even number of values
        if (keyValuesToMonitor.length % 2 != 0) {
            throw new Exception("keyValuesToMonitor must include a key and value.");
        }

        String[] values = new String[keyValuesToMonitor.length / 2];
        // convert the key value pairs to values
        for (int i = 0; i < keyValuesToMonitor.length; i += 2) {
            values[i] = keyValuesToMonitor[i] + ": " + keyValuesToMonitor[i + 1];
        }
        this.log(LogLevel.INFORMATION, (Object[]) values);
    }

    protected String convertToString(Object... messages) {
        String[] stringMessages = new String[messages.length];

        for (int i = 0; i < messages.length; i++) {
            stringMessages[i] = messages[i].toString();
        }
        return String.join("\n", stringMessages);
    }
}
