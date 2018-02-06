package com.techhounds.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	private TalonSRX intake;
	private TalonSRX intakeAngler;
	
	public Intake() {
		intake.enableCurrentLimit(true);
		intake.configContinuousCurrentLimit(20, 0);
		intake = new WPI_TalonSRX(RobotMap.MOTOR_INTAKE);
	}
	
	public void setPower(double power){
		intake.set(ControlMode.PercentOutput, check(power));
	}
	
    public double check(double in){
    	return Math.min(Math.max(in, -1), 1);
    }

    public void initDefaultCommand() {
        
    }
}