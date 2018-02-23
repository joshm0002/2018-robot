package com.techhounds.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.Dashboard.DashboardUpdatable;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX intakeLeft;
	private WPI_TalonSRX intakeRight;
	
	public Intake() {
		intakeLeft = RobotUtilities.getTalonSRX(RobotMap.INTAKE_LEFT);
		intakeRight = RobotUtilities.getTalonSRX(RobotMap.INTAKE_RIGHT);
		configure(intakeLeft);
		configure(intakeRight);
	}
	
	private void configure(WPI_TalonSRX talon) {
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(20, 0);
		talon.configPeakCurrentLimit(40, 0);
		talon.configPeakCurrentDuration(250, 0);
		talon.configOpenloopRamp(0.5, 0);
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

    public void initDefaultCommand() {
    	// GamepadIntakeControl set in OI
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("IR Sensor", intakeLeft.getSensorCollection().getAnalogIn());
	}
}