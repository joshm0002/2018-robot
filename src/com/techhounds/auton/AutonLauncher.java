package com.techhounds.auton;

import com.techhounds.auton.paths.DriveDistance;
import com.techhounds.auton.paths.StraightScale;
import com.techhounds.auton.paths.StraightSwitch;

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
		STRAIGHT_SWITCH,
		STRAIGHT_SCALE
	}
	
	public static final SendableChooser<Auton> autonChoices = new SendableChooser<>();

	public static void addChoices() {
		autonChoices.addDefault("Baseline", Auton.BASELINE);
		autonChoices.addObject("Straight Switch", Auton.STRAIGHT_SWITCH);
		autonChoices.addObject("Straight Scale", Auton.STRAIGHT_SCALE);
		SmartDashboard.putData("Auton Chooser", autonChoices);
	}
	
	public static void runAuton(FieldState field) {
		switch(autonChoices.getSelected()) {
		case STRAIGHT_SWITCH:
			runStraightSwitch(field);
			break;
		case STRAIGHT_SCALE:
			runStraightScale(field);
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
		new DriveDistance(150, 3).start();
	}
	
	public static void runStraightSwitch(FieldState field) {
		if (field.getRobotPosition() == field.getSwitchPosition()) {
			new StraightSwitch().start();
		} else {
			runBaseline();
		}
	}
	
	public static void runStraightScale(FieldState field) {
		if (field.getRobotPosition() == field.getScalePosition()) {
			new StraightScale().start();
		} else {
			runBaseline();
		}
	}
	
	public static void runSwitch(FieldState field) {
		
	}
	
	public static void runScale(FieldState field) {
		
	}
	
	public static void runSwitchOrScale(FieldState field) {
		
	}
	
	public static void runScaleOrSwitch(FieldState field) {
		
	}
	
	public static void runScaleSwitch(FieldState field) {
		
	}
	
	public static void runScaleSwitchSwitch(FieldState field) {
		
	}
	
	public static void runScaleSwitchScale(FieldState field) {
		
	}
}
