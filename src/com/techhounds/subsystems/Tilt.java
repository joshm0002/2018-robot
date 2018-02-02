package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Tilt {
	
	private TalonSRX tiltMotor;
	private PIDController pid;
	
	private double P = 0, I = 0, D = 0;
	private double tolerance = 0;
	
	private void tiltMotor() {
		tiltMotor = new WPI_TalonSRX(RobotMap.MOTOR_ANGLE_INTAKE);
		tiltMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		
		pid = new PIDController(P, I, D, new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
			@Override
			public double pidGet() {
				return Math.max(Math.min(tiltMotor.getSensorCollection().getAnalogInRaw(), 0), 0);
				//value for min max yet TBD
			}
		} , new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				setPower(output < 0 ? output - .1 : output);
			}
		}, 1/200.0);
		pid.setOutputRange(0, 0);
		pid.setInputRange(0, 0);
		//value for mins maxs TBD
		pid.setAbsoluteTolerance(tolerance);
	}
	
	public void toggleTilt() {
		
	}
	
	public void setPower(double power) {
		tiltMotor.set(ControlMode.PercentOutput, power);;
	}
	
	public void setPosition(double position) {
		pid.setSetpoint(Math.max (Math.min (position, 0), 0));
		//value for min max TBD
		pid.enable();
	}
	
	public double getSetPoint() {
		return pid.getSetpoint();
	}
	
	public boolean onTarget() {
		return pid.onTarget();
	}
	
	public void disableControl() {
		pid.disable();
	}
	
	public double getPosition() {
		return tiltMotor.getSensorCollection().getAnalogIn();
	}
	
	public boolean reachedTarget(double tolerance) {
		return Math.abs(pid.getError()) < tolerance;
	}
	
	public double getError() {
		return pid.getError();
	}
	
	public void stopPower() {
		pid.disable();
		tiltMotor.set(ControlMode.PercentOutput, 0);
	}
}