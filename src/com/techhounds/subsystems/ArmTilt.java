package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmTilt extends Subsystem{
	
	private TalonSRX tiltMotor;
	
	private double P = 0, I = 0, D = 0;
	
	public void tiltMotor() {
		tiltMotor = new WPI_TalonSRX(RobotMap.TILT);
		configDefaults();
	}
	
	/**
	 * TODO: setup soft limits
	 * 
	 * Note: we won't need hard limit switches, since it'll be
	 * an analog potentiometer (we won't need to zero it)
	 */
	public void configDefaults() {
		tiltMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		tiltMotor.enableCurrentLimit(true);
		tiltMotor.configContinuousCurrentLimit(20, 0);
		tiltMotor.configPeakCurrentLimit(40, 0);
		tiltMotor.configPeakCurrentDuration(250, 0);
		tiltMotor.setNeutralMode(NeutralMode.Brake);
		tiltMotor.setInverted(false);
		tiltMotor.config_kP(0, P, 0);
		tiltMotor.config_kI(0, I, 0);
		tiltMotor.config_kD(0, D, 0);
		tiltMotor.configClosedloopRamp(0.5, 0);
		tiltMotor.configPeakOutputForward(100, 0);
		tiltMotor.configPeakOutputReverse(100, 0);
		tiltMotor.configVoltageCompSaturation(12, 0);
		tiltMotor.enableVoltageCompensation(true);
	}

	public void setPosition(double position) {
		tiltMotor.set(ControlMode.Position, position);
	}
	
	public double getPosition() {
		return tiltMotor.getSensorCollection().getAnalogIn();
	}
	
	public void stopPower() {
		tiltMotor.set(ControlMode.PercentOutput, 0);
	}

	@Override
	/**
	 * TODO: set to "down" position
	 */
	protected void initDefaultCommand() {
	}
	
}