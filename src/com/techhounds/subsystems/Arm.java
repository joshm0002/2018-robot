package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private Solenoid arm;
	
	public void arm(){
		arm = new Solenoid(RobotMap.ARMS);
	}
	
    public void toggleArm(){
    	arm.set(!arm.get());
    }
    
    public void extendArm(){
    	arm.set(true);
    }
    
    public void retractArm(){
    	arm.set(false);
    }

    public void initDefaultCommand() {
        
    }
}

