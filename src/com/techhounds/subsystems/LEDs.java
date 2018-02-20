package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	
	private final SerialPort serial = new SerialPort(9600, Port.kUSB1); // FIXME: correct port type
	
	public enum LEDState {
		GREEN(0),
		YELLOW(1),
		RED(2);
		
		public final int code;
		LEDState(int code) {
			this.code = code;
		}
	}
	
	public LEDs() {}
	
	public boolean isValid() {
		return serial != null;
	}
	
	public void set(LEDState state) {
		if (isValid()) {
			serial.writeString(String.valueOf(state.code));
		}
	}

    public void initDefaultCommand() {}
}

