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
	
	public static final double POS_UP = 540; 
	public static final double POS_MID = 470;
	public static final double POS_DOWN = 330;
	
//	public static final double POS_UP = 650; 
//	public static final double POS_MID = 550;
//	public static final double POS_DOWN = 420;
	public static final double POS_RANGE = POS_UP - POS_DOWN;
	public static final boolean DEBUG = false;
	
	private WPI_TalonSRX tiltMotor;

	public Tilt() {
		tiltMotor = RobotUtilities.getTalonSRX(RobotMap.TILT, "Tilt", "Tilt");
		configure(tiltMotor);
		tiltMotor.setInverted(false);
	}
	
	/**
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
		
		talon.setSensorPhase(true);
		talon.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
		
		// TODO enable soft limits
//		talon.overrideSoftLimitsEnable(true);
//		talon.configForwardSoftLimitEnable(true, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configReverseSoftLimitThreshold((int) (POS_UP+10), RobotUtilities.CONFIG_TIMEOUT);
//		talon.configForwardSoftLimitEnable(true, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configReverseSoftLimitThreshold((int) (POS_DOWN-10), RobotUtilities.CONFIG_TIMEOUT);
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
	public void initSD() {
		if (DEBUG) {
			SmartDashboard.putData(this);
			SmartDashboard.putData("Tilt Up", new SetTiltPosition(TiltPosition.UP));
			SmartDashboard.putData("Tilt Middle", new SetTiltPosition(TiltPosition.MIDDLE));
			SmartDashboard.putData("Tilt Down", new SetTiltPosition(TiltPosition.DOWN));
		}
	}

	@Override
	public void updateSD() {
		SmartDashboard.putNumber("Tilt Angle", getPosition());

		if (DEBUG) {
			SmartDashboard.putNumber("Tilt Power", tiltMotor.getMotorOutputPercent());
			SmartDashboard.putNumber("Tilt Error", tiltMotor.getClosedLoopError(0));
			SmartDashboard.putNumber("Tilt Current", tiltMotor.getOutputCurrent());
		}
	}
}