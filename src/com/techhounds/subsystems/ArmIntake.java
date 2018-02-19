package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.commands.Collector;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmIntake extends Subsystem {
	
	private TalonSRX intakeLeft;
	private TalonSRX intakeRight;
	
	public ArmIntake() {
		intakeLeft = new WPI_TalonSRX(RobotMap.INTAKE_LEFT);
		intakeRight = new WPI_TalonSRX(RobotMap.INTAKE_RIGHT);
		configDefaults();
	}
	
	public void configDefaults() {
		intakeLeft.enableCurrentLimit(true);
		intakeLeft.configContinuousCurrentLimit(20, 0);
		intakeLeft.configPeakCurrentLimit(40, 0);
		intakeLeft.configPeakCurrentDuration(250, 0);
		intakeLeft.setInverted(false);
		intakeLeft.setNeutralMode(NeutralMode.Coast);
		intakeLeft.configOpenloopRamp(0.5, 0);
		intakeLeft.configPeakOutputForward(100, 0);
		intakeLeft.configPeakOutputReverse(100, 0);
		intakeLeft.configVoltageCompSaturation(12, 0);
		intakeLeft.enableVoltageCompensation(true);
		
		intakeRight.enableCurrentLimit(true);
		intakeRight.configContinuousCurrentLimit(20, 0);
		intakeRight.configPeakCurrentDuration(250, 0);
		intakeRight.setInverted(false);
		intakeRight.setNeutralMode(NeutralMode.Coast);
		intakeRight.configOpenloopRamp(0.5, 0);
		intakeRight.configPeakOutputForward(100, 0);
		intakeRight.configPeakOutputReverse(100, 0);
		intakeRight.configVoltageCompSaturation(12, 0);
		intakeRight.enableVoltageCompensation(true);
	}
	
	public void setPower(double power){
		intakeLeft.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
		intakeRight.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Collector(0));
    }
}