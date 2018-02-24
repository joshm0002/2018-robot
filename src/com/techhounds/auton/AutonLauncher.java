package com.techhounds.auton;

/**
 * This is the main entry point for our match autonomous mode.
 * This command should be constructed & started at the start of auton -
 * constructing it too soon means it won't be able to read from the SmartDashboard
 * to start the correct auton mode.
 */
public class AutonLauncher {

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
		new CenterToLeftSwitch().start();;
	}
}
