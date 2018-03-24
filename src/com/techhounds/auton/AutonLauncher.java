package com.techhounds.auton;

import com.techhounds.auton.FieldState.Position;
import com.techhounds.auton.paths.Baseline;
import com.techhounds.auton.paths.CenterLeftSwitch;
import com.techhounds.auton.paths.CenterRightSwitch;
import com.techhounds.auton.paths.LeftScale;
import com.techhounds.auton.paths.LeftScaleCross;
import com.techhounds.auton.paths.LeftScaleScale;
import com.techhounds.auton.paths.LeftScaleSwitch;
import com.techhounds.auton.paths.LeftSwitch;
import com.techhounds.auton.paths.RightScale;
import com.techhounds.auton.paths.RightScaleCross;
import com.techhounds.auton.paths.RightScaleScale;
import com.techhounds.auton.paths.RightScaleSwitch;
import com.techhounds.auton.paths.RightSwitch;
import com.techhounds.auton.paths.StraightSwitch;

import edu.wpi.first.wpilibj.command.Command;
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
		SIDE_SCALE, // NOT DONE
		SWITCH_OR_SCALE,
		SCALE_OR_SWITCH,
		SCALE_AND_SWITCH,
		SCALE_SCALE
	}
	
	public static final SendableChooser<Auton> autonChoices = new SendableChooser<>();

	public static void addChoices() {
		autonChoices.addDefault("Baseline", Auton.BASELINE);
		autonChoices.addObject("Front Switch", Auton.FRONT_SWITCH);
		autonChoices.addObject("Front Scale", Auton.FRONT_SCALE);
		autonChoices.addObject("Side Switch", Auton.SIDE_SWITCH);
		autonChoices.addObject("Switch or Scale", Auton.SWITCH_OR_SCALE);
		autonChoices.addObject("Scale or Switch", Auton.SCALE_OR_SWITCH);
		autonChoices.addObject("Scale and Switch", Auton.SCALE_AND_SWITCH);
		autonChoices.addObject("Double Scale", Auton.SCALE_SCALE);
		SmartDashboard.putData("Auton Chooser", autonChoices);
	}
	
	public static Command getAuton(FieldState field) {
		System.out.println("Running Auton " + autonChoices.getSelected().toString());
		switch(autonChoices.getSelected()) {
		case SCALE_SCALE:
			return getDoubleScale(field);
		case SCALE_AND_SWITCH:
			return getScaleAndSwitch(field);
		case SWITCH_OR_SCALE:
			return getSwitchOrScale(field);
		case SCALE_OR_SWITCH:
			return getScaleOrSwitch(field);
		case FRONT_SWITCH:
			return getFrontSwitch(field);
		case FRONT_SCALE:
			return getFrontScale(field);
		case SIDE_SWITCH:
			return getSideSwitch(field);
		case BASELINE:
			return getBaseline();
		default:
			return getBaseline();
		}
	}
	
	public static Command getBaseline() {
		return new Baseline();
	}
	
	public static Command getFrontSwitch(FieldState field) {
		// Start center, we can do either
		if (field.getRobotPosition() == Position.Middle) {
			if (field.getSwitchPosition() == Position.Right) {
				return new CenterRightSwitch();
			} else if (field.getSwitchPosition() == Position.Left) {
				return new CenterLeftSwitch();
			} else {
				return getBaseline();
			}
		// we're on one side, so either do it straight ahead or do neither
		} else if (field.getRobotPosition() == field.getSwitchPosition()) {
			return new StraightSwitch();
		} else {
			return getBaseline();
		}
	}
	
	public static Command getFrontScale(FieldState field) {
		if (field.getRobotPosition() == Position.Right) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScale();
			} else {
				return new LeftScaleCross();
			}
		} else {
			if (field.getScalePosition() == Position.Right) {
				return new RightScaleCross();
			} else {
				return new LeftScale();
			}
		}
	}
	
	public static Command getSideSwitch(FieldState field) {
		if (field.getRobotPosition() == field.getSwitchPosition()) {
			if (field.getSwitchPosition() == Position.Right) {
				return new RightSwitch();
			} else if (field.getSwitchPosition() == Position.Left) {
				return new LeftSwitch();
			} else {
				return getBaseline();
			}
		} else {
			return getBaseline();
		}
	}
	
//	public static void runSideScale(FieldState field) {
//		
//	}

	public static Command getSwitchOrScale(FieldState field) {
		if (field.getRobotPosition() == field.getSwitchPosition()) {
			return getSideSwitch(field);
		} else if (field.getRobotPosition() == field.getScalePosition()) {
			return getFrontScale(field);
		} else {
			return getBaseline();
		}
	}

	public static Command getScaleOrSwitch(FieldState field) {
		if (field.getRobotPosition() == field.getScalePosition()) {
			return getFrontScale(field);
		} else if (field.getRobotPosition() == field.getSwitchPosition()) {
			return getSideSwitch(field);
		} else {
			return getBaseline();
		}
	}
	
	public static Command getScaleAndSwitch(FieldState field) {
		if (field.getScalePosition() == field.getRobotPosition() && field.getScalePosition() == field.getRobotPosition()) {
			if (field.getRobotPosition() == Position.Right) {
				return new RightScaleSwitch();
			} else {
				return new LeftScaleSwitch();
			}
		} else {
			return getScaleOrSwitch(field);
		}
	}
	
	/**
	 * TODO: do cross scale or switch if it isn't our side?
	 * @param field
	 * @return
	 */
	public static Command getDoubleScale(FieldState field) {
		if (field.getScalePosition() == field.getRobotPosition()) {
			if (field.getRobotPosition() == Position.Right) {
				return new RightScaleScale();
			} else {
				return new LeftScaleScale();
			}
		} else {
			return getFrontScale(field);
		}
	}
	
//	public static void runScaleSwitchSwitch(FieldState field) {
//		
//	}
//	
//	public static void runScaleSwitchScale(FieldState field) {
//		
//	}
}
