package com.techhounds.auton;

import com.techhounds.auton.FieldState.Position;
import com.techhounds.auton.paths.Baseline;
import com.techhounds.auton.paths.CenterLeftSwitchDouble;
import com.techhounds.auton.paths.CenterRightSwitchDouble;
import com.techhounds.auton.paths.LeftScaleCross;
import com.techhounds.auton.paths.LeftScaleScale;
import com.techhounds.auton.paths.LeftSwitch;
import com.techhounds.auton.paths.RightScaleCross;
import com.techhounds.auton.paths.RightScaleScale;
import com.techhounds.auton.paths.RightSwitch;

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

	enum Objective {
		SCALE,
		SWITCH,
		BASELINE,
	}

	public static final SendableChooser<Objective> firstPriority = new SendableChooser<>();
	public static final SendableChooser<Boolean> enableCross = new SendableChooser<>();

	public static void addChoices() {
		firstPriority.addDefault("Baseline", Objective.BASELINE);
		firstPriority.addObject("Switch", Objective.SWITCH);
		firstPriority.addObject("Scale", Objective.SCALE);
		SmartDashboard.putData("First Cube Priority", firstPriority);

		enableCross.addDefault("Enable Cross", new Boolean(true));
		enableCross.addObject("Disable Cross", new Boolean(false));
		SmartDashboard.putData("Enable Midfield Crossing", enableCross);
	}

	public static Command getAuton(FieldState field) {
		switch(firstPriority.getSelected()) {
		case BASELINE:
			return getBaseline();
		case SWITCH:
			return getSwitch(field);
		case SCALE:
			if (enableCross.getSelected()) {
				return getAnyScale(field);
			} else {
				return getAheadScale(field);
			}
		default:
			return getBaseline();
		}
	}

	public static Command getBaseline() {
		return new Baseline();
	}

	public static Command getSwitch(FieldState field) {
		if (field.getRobotPosition() == Position.Middle) {
			if (field.getSwitchPosition() == Position.Right) {
				return new CenterRightSwitchDouble();
//				return new CenterRightSwitch();
			} else if (field.getSwitchPosition() == Position.Left) {
				return new CenterLeftSwitchDouble();
//				return new CenterLeftSwitch();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotPosition() == Position.Right && field.getSwitchPosition() == Position.Right) {
			return new RightSwitch();

		} else if (field.getRobotPosition() == Position.Left && field.getSwitchPosition() == Position.Left) {
			return new LeftSwitch();
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAnyScale(FieldState field) {
		if (field.getRobotPosition() == Position.Right) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScaleScale();
			} else if (field.getScalePosition() == Position.Left) {
				return new LeftScaleCross();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotPosition() == Position.Left) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScaleCross();
			} else if (field.getScalePosition() == Position.Left) {
				return new LeftScaleScale();
			} else {
				return getBaseline();
			}
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAheadScale(FieldState field) {
		if (field.getRobotPosition() == field.getScalePosition()) {
			return getAnyScale(field);
		} else {
			return getSwitch(field);
		}
	}
}
