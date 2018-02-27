package com.techhounds.tilt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Tilt extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX tiltMotor;
	
	public Tilt() {
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
		
//		talon.enableCurrentLimit(true);
//		talon.configContinuousCurrentLimit(20, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakCurrentLimit(40, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakCurrentDuration(250, RobotUtilities.CONFIG_TIMEOUT);
		talon.setNeutralMode(NeutralMode.Brake);
		
		// forward is down, backwards is up
		talon.setSensorPhase(true);
		
//		talon.configPeakOutputForward(0.5, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakOutputReverse(0.5, RobotUtilities.CONFIG_TIMEOUT);
		// TODO nominal limits
	}

	public void setPosition(double position) {
		tiltMotor.set(ControlMode.Position, position);
	}
	
	public void setPower(double power) {
		tiltMotor.set(ControlMode.PercentOutput, power);
	}
	
	public double getPosition() {
		return tiltMotor.getSelectedSensorPosition(0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetTiltHold());
	}

	@Override
	public void initSD() {}

	@Override
	public void updateSD() {}

	@Override
	public void initDebugSD() {
		SmartDashboard.putData(this);
		SmartDashboard.putData("Tilt Up", new SetTiltPosition(TiltPosition.UP));
		SmartDashboard.putData("Tilt Middle", new SetTiltPosition(TiltPosition.MIDDLE));
		SmartDashboard.putData("Tilt Down", new SetTiltPosition(TiltPosition.DOWN));
		SmartDashboard.putData("Tilt Hold", new SetTiltHold());
		
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("Tilt Angle", tiltMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Tilt Power", tiltMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Tilt Error", tiltMotor.getClosedLoopError(0));
		SmartDashboard.putNumber("Tilt Current", tiltMotor.getOutputCurrent());
		
	}
	
}