package com.techhounds;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotUtilities {
	/**
	 * Limits the given value to -1, 1
	 * 
	 * @param value
	 * @return
	 */
	public static double constrain(double value) {
		return Math.min(Math.max(value, -1), 1);
	}
	
	public static WPI_TalonSRX getTalonSRX(int port) {
		WPI_TalonSRX talon = new WPI_TalonSRX(port);
		configureDefaults(talon);
		return talon;
	}
	
	public static void configureDefaults(WPI_TalonSRX talon) {
		// OUTPUTS
		talon.setInverted(false);
		talon.setNeutralMode(NeutralMode.Coast);
		talon.configPeakOutputForward(100, 0);
		talon.configPeakOutputReverse(-100, 0);
//		talon.configNeutralDeadband(0, 0);
//		talon.configNominalOutputForward(100, 0);
//		talon.configNominalOutputReverse(-100, 0);
		
		// POWER CONTROL
		talon.enableCurrentLimit(false);
		talon.configContinuousCurrentLimit(0, 0);
		talon.configPeakCurrentDuration(0, 0);
		talon.configVoltageCompSaturation(12, 0);
		talon.enableVoltageCompensation(true);
		talon.configOpenloopRamp(0, 0);
//		talon.configVoltageMeasurementFilter(5, 0);

		// WPIlib FEATURES
//		talon.setExpiration(0);
		talon.setSafetyEnabled(false);
		
		// SENSORS
		talon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, 
											 LimitSwitchNormal.Disabled, 0);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, 
											 LimitSwitchNormal.Disabled, 0);
//		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 0);
//		talon.configVelocityMeasurementWindow(5, 0);
		talon.configForwardSoftLimitEnable(false, 0);
		talon.configForwardSoftLimitThreshold(0, 0);
//		talon.configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal, timeoutMs)
		talon.configReverseSoftLimitEnable(false, 0);
		talon.configReverseSoftLimitThreshold(0, 0);
		talon.configSelectedFeedbackSensor(FeedbackDevice.None, 0, 0);
//		talon.configSensorTerm(sensorTerm, feedbackDevice, timeoutMs)
//		talon.overrideLimitSwitchesEnable(false);
//		talon.overrideSoftLimitsEnable(false);
		
		// MOTION PROFILING & MAGIC
		talon.changeMotionControlFramePeriod(5);
//		talon.configMotionAcceleration(1, 0);
//		talon.configMotionCruiseVelocity(1, 0);
		talon.configMotionProfileTrajectoryPeriod(5, 0);
		
		// PID CONTROLS
		talon.config_kP(0, 0, 0);
		talon.config_kI(0, 0, 0);
		talon.config_kD(0, 0, 0);
//		talon.config_IntegralZone(0, 0, 0);
		talon.configAllowableClosedloopError(0, 10, 0);
		talon.configClosedloopRamp(0, 0);
//		talon.configMaxIntegralAccumulator(0, 0, 0);
//		talon.setIntegralAccumulator(0, 0, 0);
	}
}
