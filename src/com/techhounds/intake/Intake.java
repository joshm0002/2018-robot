package com.techhounds.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX intakeLeft;
	private WPI_TalonSRX intakeRight;
	
	public static final boolean DEBUG = false;
	
	public Intake() {
		intakeLeft = RobotUtilities.getTalonSRX(RobotMap.INTAKE_LEFT, "Intake", "Left (Breakout)");
		intakeRight = RobotUtilities.getTalonSRX(RobotMap.INTAKE_RIGHT, "Intake", "Right");
		configure(intakeLeft);
		configure(intakeRight);
	}
	
	private void configure(WPI_TalonSRX talon) {
		talon.configOpenloopRamp(0.1, RobotUtilities.CONFIG_TIMEOUT);
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, RobotUtilities.CONFIG_TIMEOUT);
		talon.overrideLimitSwitchesEnable(false);
	}
	
	public void setPower(double power){
		intakeLeft.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
		intakeRight.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}
	
	public double getLeftPower() {
		return intakeLeft.get();
	}
	
	public double getRightPower() {
		return intakeRight.get();
	}
	
	public boolean isCubeDetected() {
		return intakeLeft.getSensorCollection().isFwdLimitSwitchClosed();
	}

    public void initDefaultCommand() {
    	// GamepadIntakeControl set in OI
    }

	@Override
	public void initSD() {
		if (DEBUG) {
			SmartDashboard.putData(this);
			SmartDashboard.putData("Intake In", new SetIntakePower(1));
			SmartDashboard.putData("Intake Out", new SetIntakePower(-1));
		}
	}

	@Override
	public void updateSD() {
		SmartDashboard.putBoolean("Cube Detected", isCubeDetected());
		
		if (DEBUG) {
			SmartDashboard.putNumber("Intake Power", intakeLeft.getMotorOutputPercent());
			SmartDashboard.putNumber("Intake Current", intakeLeft.getOutputCurrent());
		}
	}
}