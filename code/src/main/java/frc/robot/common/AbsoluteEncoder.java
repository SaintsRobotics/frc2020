package frc.robot.common;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AbsoluteEncoder {
	/**
	 * this class is mainly a wrapper for accessing the current position of rotation
	 * that the motor is at it uses the potentiometer values as voltage and
	 * correlates them to rotation degrees
	 */

	private AnalogInput analogIn;
	private double offset = 0; // the offset from zero for each motor
	private boolean isInverted;
	private double voltageToDegrees = 72;
	private AnalogEncoder x;

	/**
	 * Constructor for an absolute encoder
	 * 
	 * @param channel    Analog port for the Encoder
	 * @param isInverted Changes which direction increases/decreases the encoder
	 */
	public AbsoluteEncoder(int channel, boolean isInverted) {
		analogIn = new AnalogInput(channel);
		this.x = new AnalogEncoder(analogIn);
		this.isInverted = isInverted;
	}

	/**
	 * Gets rotation in degrees
	 * 
	 * @return the position of encoder in degrees
	 */
	public double get() {
		if (isInverted) {
			return ((5 - analogIn.getVoltage()) * this.voltageToDegrees) - this.offset;
		}

		else {
			return (analogIn.getVoltage() * this.voltageToDegrees) - this.offset;
		}
	}

	public void reset() {
		this.offset = analogIn.getVoltage() * this.voltageToDegrees;
	}

}
