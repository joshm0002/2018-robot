package com.techhounds.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmIntake extends Subsystem {
	
	private WPI_TalonSRX intakeLeft;
	private WPI_TalonSRX intakeRight;
	
	public ArmIntake() {
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

    public void initDefaultCommand() {
    	setDefaultCommand(new Collector(0));
    }
}