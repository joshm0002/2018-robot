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
		subsystems.add(Robot.field);
		
		for (DashboardUpdatable subsystem : subsystems) {
			subsystem.initSD();
		}
		
		SmartDashboard.putData("Toggle Compressor", new ToggleCompressor());
	}
	
	public static void updateDashboard() {
		for (DashboardUpdatable subsystem : subsystems) {
			subsystem.updateSD();
		}
		
		SmartDashboard.putNumber("Dashboard Update Counts", updateCounts++);
	}
	
	public interface DashboardUpdatable {
		public void initSD();
		public void updateSD();
	}
}
