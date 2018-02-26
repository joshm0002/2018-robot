package com.techhounds.auton;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the main entry point for our match autonomous mode.
 * This command should be constructed & started at the start of auton -
 * constructing it too soon means it won't be able to read from the SmartDashboard
 * to start the correct auton mode.
 */
public class AutonLauncher {
	
	enum Auton {
		BASELINE,
		STRAIGHT_SWITCH
	}
	
	public static final SendableChooser<Auton> autonChoices = new SendableChooser<>();

	public static void addChoices() {
		autonChoices.addDefault("Baseline", Auton.BASELINE);
		autonChoices.addObject("Straight Switch", Auton.STRAIGHT_SWITCH);
		SmartDashboard.putData("Auton Chooser", autonChoices);
	}
	
	public static void runAuton(FieldState field) {
		switch(autonChoices.getSelected()) {
		case STRAIGHT_SWITCH:
			runStraightSwitch(field);
			break;
		case BASELINE:
			runBaseline();
			break;
		default:
			runBaseline();
			break;
		}
	}
	
	public static void runBaseline() {
		
	}
	
	public static void runStraightSwitch(FieldState field) {
		if (field.getRobotPosition() == field.getSwitchPosition()) {
			
		} else {
			runBaseline();
		}
	}
}
