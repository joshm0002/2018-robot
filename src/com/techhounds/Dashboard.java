package com.techhounds;

import java.util.ArrayList;
import java.util.List;

import com.techhounds.compressor.ToggleCompressor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for updating the smart dashboard periodically during the match.
 *
 */
public class Dashboard {
	
	private static List<DashboardUpdatable> subsystems = new ArrayList<DashboardUpdatable>();
	public static final boolean DEBUG = true;
	private static int updateCounts = 0;
	
	public static void clearDashboard() {
		for(String key : SmartDashboard.getKeys()) {
			SmartDashboard.delete(key);
		}
	}
	
	public static void initDashboard() {
		subsystems.add(Robot.arm);
		subsystems.add(Robot.drivetrain);
		subsystems.add(Robot.intake);
		subsystems.add(Robot.powerPack);
		subsystems.add(Robot.transmission);
		subsystems.add(Robot.tilt);
		subsystems.add(Robot.hook);
		
		for (DashboardUpdatable subsystem : subsystems) {
			subsystem.initSD();
			if (DEBUG) {
				subsystem.initDebugSD();
			}
		}
		
		SmartDashboard.putData("Toggle Compressor", new ToggleCompressor());
	}
	
	public static void updateDashboard() {
		for (DashboardUpdatable subsystem : subsystems) {
			subsystem.updateSD();
			if (DEBUG) {
				subsystem.updateDebugSD();
			}
		}
		
		// FIXME: appears to never get here, possibly Drive or Intake (IR sensor)
		SmartDashboard.putNumber("Dashboard Update Counts", updateCounts++);
		
		SmartDashboard.putBoolean("Compressor Closed Loop Control", Robot.compressor.getClosedLoopControl());
		SmartDashboard.putBoolean("Compressor Enabled", Robot.compressor.enabled());
		SmartDashboard.putBoolean("Compressor Pressure Switch", Robot.compressor.getPressureSwitchValue());
	}
	
	public interface DashboardUpdatable {
		public void initSD();
		public void updateSD();
		public void initDebugSD();
		public void updateDebugSD();
	}
}
