package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;

public class Tilt extends Subsystem{
	
	private TalonSRX tiltMotor;
	
	private double P = 0, I = 0, D = 0;
	
	public void tiltMotor() {
		tiltMotor = new WPI_TalonSRX(RobotMap.TILT);
		configDefaults();
	}
	
	public void configDefaults() {
		tiltMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		tiltMotor.enableCurrentLimit(true);
		tiltMotor.configContinuousCurrentLimit(20, 0);
		tiltMotor.config_kP(0, P, 0);
		tiltMotor.config_kI(0, I, 0);
		tiltMotor.config_kD(0, D, 0);
	}
	
	public void setPosition(double position) {
		tiltMotor.set(ControlMode.Position, position);
		//value for min max TBD
	}
	
	public double getPosition() {
		return tiltMotor.getSensorCollection().getAnalogIn();
	}
	
	public void stopPower() {
		tiltMotor.set(ControlMode.PercentOutput, 0);
	}
	
}