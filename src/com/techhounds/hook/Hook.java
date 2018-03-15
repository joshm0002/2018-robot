package com.techhounds.hook;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hook extends Subsystem implements DashboardUpdatable {
	
	private final Solenoid hook = new Solenoid(RobotMap.HOOK);
	
	public static final boolean DEBUG = false;
	
	public Hook() {
	}
	
	public void setPosition(boolean up) {
		hook.set(up);
	}
	
	public boolean isUp() {
		return hook.get();
	}
	
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
		}
	}
}