package com.techhounds.leds;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	
	private SerialPort serial;
	
	public LEDs() {
		try {
			serial = new SerialPort(9600, Port.kUSB1);
		} catch (Throwable t) {
			t.printStackTrace();
			serial = null;
		}
	}
	
	public boolean isValid() {
		return serial != null;
	}
	
	public void set(int red, int green, int blue) {
		if (isValid()) {
			serial.write(new byte[] { (byte)red, (byte)green, (byte)blue }, 3);
		}
	}

    public void initDefaultCommand() {}
}

