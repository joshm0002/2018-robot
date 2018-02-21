package com.techhounds.arm;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents the pneumatic arms that squeeze the box.
 */
public class Arm extends Subsystem {
	
	private Solenoid arm;
	
	public Arm(){
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
    
    public void setArm(boolean open) {
    	arm.set(open);
    }
    
    public boolean isOpen() {
    	return arm.get();
    }

    /**
     * Intentionally left blank: default state of
     * pneumatics is determined by tubing connections.
     */
    public void initDefaultCommand() {}
}
