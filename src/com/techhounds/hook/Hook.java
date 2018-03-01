package com.techhounds.hook;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hook extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX hookMotor;
	public static final double PEAK_FWD = 0.35;
	public static final double PEAK_REV = -0.35;
	
	public Hook() {
		hookMotor = RobotUtilities.getTalonSRX(RobotMap.HOOK_MOTOR, "Hook", "Hook");
		configure(hookMotor);
	}
	
	private void configure(WPI_TalonSRX talon) {
		
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotUtilities.CONFIG_TIMEOUT);
		
		// TODO: set sensor phase
		
		talon.configForwardSoftLimitThreshold(330000, RobotUtilities.CONFIG_TIMEOUT);
		talon.configReverseSoftLimitThreshold(0, RobotUtilities.CONFIG_TIMEOUT);
		talon.configForwardSoftLimitEnable(true, RobotUtilities.CONFIG_TIMEOUT);
		talon.configReverseSoftLimitEnable(true, RobotUtilities.CONFIG_TIMEOUT);
		talon.overrideSoftLimitsEnable(true);
		
		talon.configPeakOutputForward(PEAK_FWD, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakOutputReverse(PEAK_REV, RobotUtilities.CONFIG_TIMEOUT);
	}
	
	public void setPower(double power) {
		hookMotor.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}

	public double getPosition() {
		return hookMotor.getSelectedSensorPosition(0);
	}
	
    public void initDefaultCommand() {
    	// GamepadHookControl set in OI
    }

	@Override
	public void initSD() {}

	@Override
	public void updateSD() {}

	@Override
	public void initDebugSD() {
		SmartDashboard.putData(this);
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("Hook Motor Power", hookMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Hook Motor Current", hookMotor.getOutputCurrent());
		SmartDashboard.putNumber("Hook Motor Position", getPosition());
	}
}

