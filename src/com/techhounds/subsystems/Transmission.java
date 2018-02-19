package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Transmission extends Subsystem {

    private Solenoid shifter = new Solenoid(RobotMap.DRIVE_TRANSMISSION);
    private boolean state = false; //is this actually the default? should we call get()?
    
    public void toggle() {
    	shift(!state);
    }
    
    public void shift(boolean highGear) {
    	if(highGear && !state) {
    		shifter.set(true);
    	} else if (!highGear && state) {
    		shifter.set(false);
    	}
    	
    	state = highGear;
    }

    public void initDefaultCommand() {
//        setDefaultCommand(new AutoShift());
    }
}

