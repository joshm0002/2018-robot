package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotionProfileUploader implements Runnable {
	
	TalonSRX talon;
	
	public MotionProfileUploader(TalonSRX talon) {
		this.talon = talon;
	}

	@Override
	public void run() {
		talon.processMotionProfileBuffer();
	}

}
