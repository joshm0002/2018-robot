package com.techhounds.hook;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
	public static final double PEAK_FWD = 0.5;
	public static final double PEAK_REV = -0.5;
	
	public Hook() {
		hookMotor = RobotUtilities.getTalonSRX(RobotMap.HOOK_MOTOR);
		configure(hookMotor);
	}
	
	private void configure(WPI_TalonSRX talon) {
		talon.configPeakOutputForward(PEAK_FWD, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakOutputReverse(PEAK_REV, RobotUtilities.CONFIG_TIMEOUT);
	}
	
	public void setPower(double power) {
		hookMotor.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
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
	}
}

