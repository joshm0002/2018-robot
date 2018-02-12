package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	private TalonSRX intakeLeft;
	private TalonSRX intakeRight;
	
	public Intake() {
		intakeLeft = new WPI_TalonSRX(RobotMap.INTAKE_LEFT);
		intakeRight = new WPI_TalonSRX(RobotMap.INTAKE_RIGHT);
		configDefaults();
	}
	
	/**
	 * TODO: peak power limitation
	 * TODO: voltage limitation/ramping
	 * TODO: inverted
	 */
	public void configDefaults() {
		intakeLeft.enableCurrentLimit(true);
		intakeLeft.configContinuousCurrentLimit(20, 0);
		intakeRight.enableCurrentLimit(true);
		intakeRight.configContinuousCurrentLimit(20, 0);
	}
	
	public void setPower(double power){
		intakeLeft.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
		intakeRight.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}

    public void initDefaultCommand() {}
}