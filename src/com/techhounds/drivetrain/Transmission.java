package com.techhounds.drivetrain;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Transmission extends Subsystem implements DashboardUpdatable {

    private Solenoid shifter = new Solenoid(RobotMap.DRIVE_TRANSMISSION);
    
    public void toggle() {
    	setHighGear(!isHighGear());
    }
    
    public void setHighGear(boolean highGear) {
    	shifter.set(highGear);
    }
    
    public boolean isHighGear() {
    	return shifter.get();
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

