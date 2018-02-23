package com.techhounds;

import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	public static void updateDashboard() {
		
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
		
		SmartDashboard.putNumber("Dashboard Update Counts", updateCounts++);
	}
	
	public interface DashboardUpdatable {
		public void initSD();
		public void updateSD();
		public void initDebugSD();
		public void updateDebugSD();
	}
}
