package com.techhounds.auton;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;


/**
 * This is the main entry point for our match autonomous mode.
 * This command should be constructed & started at the start of auton -
 * constructing it too soon means it won't be able to read from the SmartDashboard
 * to start the correct auton mode.
 */
public class AutonLauncher {
	
	SendableChooser autonChooser;
	
	ArrayList<AutonOption> options; 
	
	public AutonLauncher () {
		options = new ArrayList<AutonOption>();
		
		options.add(new CenterToLeftSwitch());
		
		
		autonChooser = new SendableChooser();
		for (AutonOption option : options) {
			autonChooser.addObject(option.name, option);
		}		
		SmartDashboard.putData("Auton Mode", autonChooser);
	}

	public enum StartingPosition {
		Left, Center, Right, Unknown
	}
	
	public enum Side {
		Left, Right, Unknown
	}
	
	public static void addAutonChoices() {

	}
	
	public static StartingPosition getStartingPosition() {
		return StartingPosition.Unknown;
	}
	
	public static Side getScaleSide() {
		return Side.Unknown;
	}
	
	public static Side getSwitchSide() {
		return Side.Unknown;
	}

	public static void runAuton() {
		Scheduler.getInstance().run();
	}
}
