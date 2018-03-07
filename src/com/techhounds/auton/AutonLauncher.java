package com.techhounds.auton;

import com.techhounds.auton.FieldState.Position;
import com.techhounds.auton.paths.Baseline;
import com.techhounds.auton.paths.CenterLeftSwitch;
import com.techhounds.auton.paths.CenterRightSwitch;
import com.techhounds.auton.paths.LeftScale;
import com.techhounds.auton.paths.LeftSwitch;
import com.techhounds.auton.paths.RightSwitch;
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
		FRONT_SWITCH,
		FRONT_SCALE,
		SIDE_SWITCH,
		SIDE_SCALE // NOT DONE
	}
	
	public static final SendableChooser<Auton> autonChoices = new SendableChooser<>();

	public static void addChoices() {
		autonChoices.addDefault("Baseline", Auton.BASELINE);
		autonChoices.addObject("Straight Switch", Auton.FRONT_SWITCH);
		autonChoices.addObject("Straight Scale", Auton.FRONT_SCALE);
		autonChoices.addObject("Side Switch", Auton.SIDE_SWITCH);
		SmartDashboard.putData("Auton Chooser", autonChoices);
	}
	
	public static void runAuton(FieldState field) {
		System.out.println("Running Auton " + autonChoices.getSelected().toString());
		switch(autonChoices.getSelected()) {
		case FRONT_SWITCH:
			runFrontSwitch(field);
			break;
		case FRONT_SCALE:
			runFrontScale(field);
			break;
		case SIDE_SWITCH:
			runSideSwitch(field);
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
		new Baseline().start();
	}
	
	public static void runFrontSwitch(FieldState field) {
		if (field.getRobotPosition() == Position.Middle) { // start in center, do either
			if (field.getSwitchPosition() == Position.Right) {
				new CenterRightSwitch().start();
			} else if (field.getSwitchPosition() == Position.Left) {
				new CenterLeftSwitch().start();
			} else {
				runBaseline();
			}
		} else if (field.getRobotPosition() == field.getSwitchPosition()) { //start on one, do it if it's ahead of us
			new StraightSwitch().start();
		} else {
			runBaseline();
		}
	}
	
	public static void runFrontScale(FieldState field) {
		if (field.getRobotPosition() == field.getScalePosition()) {
			new LeftScale().start();
		} else {
			runBaseline();
		}
	}
	
	public static void runSideSwitch(FieldState field) {
		if (field.getRobotPosition() == field.getSwitchPosition()) {
			if (field.getSwitchPosition() == Position.Right) {
				new RightSwitch().start();
			} else if (field.getSwitchPosition() == Position.Left) {
				new LeftSwitch().start();
			} else {
				runBaseline();
			}
		} else {
			runBaseline();
		}
	}
	
//	public static void runSideScale(FieldState field) {
//		
//	}
//	
//	public static void runSwitchOrScale(FieldState field) {
//		
//	}
//	
//	public static void runScaleOrSwitch(FieldState field) {
//		
//	}
//	
//	public static void runScaleSwitch(FieldState field) {
//		
//	}
//	
//	public static void runScaleSwitchSwitch(FieldState field) {
//		
//	}
//	
//	public static void runScaleSwitchScale(FieldState field) {
//		
//	}
}
