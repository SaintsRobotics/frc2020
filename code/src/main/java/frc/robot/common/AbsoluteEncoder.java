package frc.robot.common;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogInput;

public class AbsoluteEncoder {
	/**
	 * this class is mainly a wrapper for accessing the current position of rotation
	 * that the motor is at it uses the potentiometer values as voltage and
	 * correlates them to rotation degrees
	 */

	private AnalogInput analogIn;
	private double m_offset = 0; // the offset from zero for each motor
	private boolean isInverted;
	private double voltageToDegrees = 72;
	private AnalogEncoder x;

	/**
	 * Constructor for an absolute encoder
	 * 
	 * @param channel    Analog port for the Encoder
	 * @param offset     the value subtracted from the raw encoder value
	 * @param isInverted Changes which direction increases/decreases the encoder
	 */
	public AbsoluteEncoder(int channel, double offset, boolean isInverted) {
		analogIn = new AnalogInput(channel);
		this.x = new AnalogEncoder(analogIn);
		this.isInverted = isInverted;
		this.m_offset = offset;
	}

	/**
	 * Gets rotation in degrees
	 * 
	 * @return the position of encoder in degrees
	 */
	public double getDegrees() {
		if (isInverted) {
			return ((5 - analogIn.getVoltage()) * this.voltageToDegrees);
		}

		else {
			return (analogIn.getVoltage() * this.voltageToDegrees);
		}
	}

	public double getRadians() {
		return Math.toRadians(getDegrees()) - this.m_offset;
	}

	public void reset() {
		this.m_offset = analogIn.getVoltage() * this.voltageToDegrees;
	}

}
