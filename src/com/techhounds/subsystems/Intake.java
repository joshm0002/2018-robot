package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	private TalonSRX intakeLeft;
	private TalonSRX intakeRight;
	
	public Intake() {
		intakeLeft = new WPI_TalonSRX(22);
		intakeLeft.enableCurrentLimit(true);
		intakeLeft.configContinuousCurrentLimit(20, 0);
		intakeLeft = new WPI_TalonSRX(RobotMap.MOTOR_INTAKE);
		intakeRight = new WPI_TalonSRX(22);
		intakeRight.enableCurrentLimit(true);
		intakeRight.configContinuousCurrentLimit(20, 0);
		intakeRight = new WPI_TalonSRX(RobotMap.MOTOR_INTAKE);
	}
	
	public void setPower(double power){
		intakeLeft.set(ControlMode.PercentOutput, check(power));
		intakeRight.set(ControlMode.PercentOutput, check(power));
	}
	
    public double check(double in){
    	return Math.min(Math.max(in, -1), 1);
    }

    public void initDefaultCommand() {
        
    }
}