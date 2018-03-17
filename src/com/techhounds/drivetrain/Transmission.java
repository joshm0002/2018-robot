package com.techhounds.drivetrain;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Transmission extends Subsystem implements DashboardUpdatable {

    private Solenoid shifter = new Solenoid(RobotMap.DRIVE_TRANSMISSION);
    
    public static final boolean DEBUG = false;
    
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
		if (DEBUG) {
			SmartDashboard.putData(this);
		}
	}

	@Override
	public void updateSD() {
		if (DEBUG) {
			SmartDashboard.putBoolean("Transmission High Gear", shifter.get());
		}
	}
}

