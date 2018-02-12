package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents the pneumatic arms that squeeze the box.
 * 
 * TODO: may need to invert positions (this can be done
 *       via pneumatic tubing too_
 */
public class Arm extends Subsystem {
	
	private Solenoid arm;
	
	public void arm(){
		arm = new Solenoid(RobotMap.ARMS);
	}
	
    public void toggleArm(){
    	arm.set(!arm.get());
    }
    
    public void openArm(){
    	arm.set(true);
    }
    
    public void closeArm(){
    	arm.set(false);
    }
    
    public boolean isOpen() {
    	return arm.get();
    }

    public void initDefaultCommand() {}
}
