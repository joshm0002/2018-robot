package com.techhounds.leds;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	
	private final SerialPort serial = new SerialPort(9600, Port.kUSB1); // FIXME: correct port type
	
	public LEDs() {}
	
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

