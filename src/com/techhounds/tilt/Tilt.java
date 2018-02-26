package com.techhounds.tilt;

import com.ctre.phoenix.ParamEnum;
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
	
	// FIXME: non-cont. values
	public static final double POS_UP = 650; //658
	public static final double POS_MID = 525; //551 is max
	public static final double POS_DOWN = 415; //413
	public static final double POS_RANGE = POS_UP - POS_DOWN;
	
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
		talon.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
		
//		talon.configPeakOutputForward(0.5, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakOutputReverse(0.5, RobotUtilities.CONFIG_TIMEOUT);
		// TODO nominal limits
	}
	
	public void setPower(double power) {
		tiltMotor.set(ControlMode.PercentOutput, power);
	}
	
	public double getPosition() {
		return Math.floorMod(tiltMotor.getSelectedSensorPosition(0), 1023);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetTiltPosition(TiltPosition.DOWN));
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
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("Tilt Angle", getPosition());
		SmartDashboard.putNumber("Tilt Power", tiltMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Tilt Error", tiltMotor.getClosedLoopError(0));
		SmartDashboard.putNumber("Tilt Current", tiltMotor.getOutputCurrent());
	}
}