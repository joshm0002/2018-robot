package com.techhounds.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for updating the smart dashboard periodically during the match.
 *
 */
public class Dashboard {
	public static void clearDashboard() {
		for(String key : SmartDashboard.getKeys()) {
			SmartDashboard.delete(key);
		}
	}
	
	public static void initDashboard() {
		
	}
	
	public static void updateDashboard() {
		
	}
}
