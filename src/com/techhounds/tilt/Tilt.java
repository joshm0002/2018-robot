package com.techhounds.tilt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Tilt extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX tiltMotor;
	
	private double P = 0, I = 0, D = 0;
	
	public void tiltMotor() {
		tiltMotor = RobotUtilities.getTalonSRX(RobotMap.TILT);
		configure(tiltMotor);
	}
	
	/**
	 * TODO: setup soft limits
	 * 
	 * Note: we won't need hard limit switches, since it'll be
	 * an analog potentiometer (we won't need to zero it)
	 */
	private void configure(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, RobotUtilities.CONFIG_TIMEOUT);
		
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(20, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakCurrentLimit(40, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakCurrentDuration(250, RobotUtilities.CONFIG_TIMEOUT);
		talon.setNeutralMode(NeutralMode.Brake);
		
		talon.config_kP(0, P, RobotUtilities.CONFIG_TIMEOUT);
		talon.config_kI(0, I, RobotUtilities.CONFIG_TIMEOUT);
		talon.config_kD(0, D, RobotUtilities.CONFIG_TIMEOUT);
		talon.configClosedloopRamp(0.5, RobotUtilities.CONFIG_TIMEOUT);
	}

	public void setPosition(double position) {
		tiltMotor.set(ControlMode.Position, position);
	}
	
	public void setPower(double power) {
		tiltMotor.set(ControlMode.PercentOutput, power);
	}
	
	public double getPosition() {
		return tiltMotor.getSensorCollection().getAnalogIn();
	}

	@Override
	protected void initDefaultCommand() {
		// FIXME both causes Scheduler hangs
//		setDefaultCommand(new SetTiltHold());
//		setDefaultCommand(new SetTiltPower(0));
	}

	@Override
	public void initSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDebugSD() {
		
	}

	@Override
	public void updateDebugSD() {
		// FIXME: this hangs!
//		SmartDashboard.putNumber("Tilt Angle", tiltMotor.getSelectedSensorPosition(0));
	}
	
}