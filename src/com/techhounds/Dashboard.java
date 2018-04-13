package com.techhounds;

import java.util.ArrayList;
import java.util.List;

import com.techhounds.compressor.ToggleCompressor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for updating the smart dashboard periodically during the match.
 *
 */
public class Dashboard {
	
	private static List<DashboardUpdatable> subsystems = new ArrayList<DashboardUpdatable>();
	private static int updateCounts = 0;
	
	private static Timer updateTimer = new Timer();
	
	public static void clearDashboard() {
		for(String key : SmartDashboard.getKeys()) {
			SmartDashboard.delete(key);
		}
	}
	
	public static void initDashboard() {
		subsystems.add(Robot.arm);
		subsystems.add(Robot.drivetrain);
		subsystems.add(Robot.gyro);
		subsystems.add(Robot.intake);
		subsystems.add(Robot.powerPack);
		subsystems.add(Robot.transmission);
		subsystems.add(Robot.tilt);
		subsystems.add(Robot.hook);
		subsystems.add(Robot.field);
		subsystems.add(Robot.gyro);
		
		for (DashboardUpdatable subsystem : subsystems) {
			subsystem.initSD();
		}
		
		SmartDashboard.putData("Toggle Compressor", new ToggleCompressor());
		
		updateTimer.start();
	}
	
	public static void updateDashboard() {
		if (updateTimer.hasPeriodPassed(0.25)) {
		
			for (DashboardUpdatable subsystem : subsystems) {
				subsystem.updateSD();
			}
			
			SmartDashboard.putNumber("Dashboard Update Counts", updateCounts++);
			SmartDashboard.putNumber("PDP Voltage", Robot.pdp.getVoltage());
			SmartDashboard.putNumber("Total Current Draw", Robot.pdp.getTotalCurrent());
			SmartDashboard.putNumber("3.3v", Robot.analog.getVoltage());
		}
	}
	
	public interface DashboardUpdatable {
		public void initSD();
		public void updateSD();
	}
}
