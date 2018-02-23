package com.techhounds.drivetrain;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: why do we bother storing the state variable?
 * Why not implement this subsystem just like Arm?
 */
public class Transmission extends Subsystem implements DashboardUpdatable {

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

    /**
     * Intentionally left blank.
     */
    public void initDefaultCommand() {}

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
		// TODO Auto-generated method stub
		
	}
}

