/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import com.techhounds.arm.ToggleArm;
import com.techhounds.drivetrain.ToggleTransmission;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final XboxController driver = new XboxController(0);
	public static final XboxController operator = new XboxController(1);
	
	public OI() {}
	
	/**
	 * Binds triggers/buttons to commands for the driver
	 */
	public static void setupDriver() {
		Button RB = new JoystickButton(driver, 6);
		RB.whenPressed(new ToggleTransmission());
	}
	
	/**
	 * Binds triggers/buttons to commands for the operator
	 */
	public static void setupOperator() {
		Button bA = new JoystickButton(operator, 1);
		// Set elevator to DOWN position
		bA.whenPressed(new SetElevatorPosition(ElevatorPosition.COLLECT));
		
		Button bB = new JoystickButton(operator, 2);
		// Set elevator to SWITCH position
		bB.whenPressed(new SetElevatorPosition(ElevatorPosition.SWITCH));
		
		Button bX = new JoystickButton(operator, 3);
		// Actuate collector arms
		bX.whenPressed(new ToggleArm());
		
		Button bY = new JoystickButton(operator, 4);
		// Set elevator to SCALE position
		bY.whenPressed(new SetElevatorPosition(ElevatorPosition.SCALE));

		Button LB = new JoystickButton(operator, 5);
		// Intake OUT
		LB.toggleWhenPressed(new SetIntakePower(-1));
		
		Button RB = new JoystickButton(operator, 6);
		// Intake IN
		RB.toggleWhenPressed(new SetIntakePower(1));
		
		Button select = new JoystickButton(operator, 7);
		// Flip out climbing hook
		
		Button start = new JoystickButton(operator, 8);
		// Enable climbing mode, climb 12"
		
		// Set arm position (UP/DOWN/45)
		
		// Manual arm position ??
		
		// Manual elevator control (I'm thinking maybe with the triggers?)
		
		// Manual climber control (I'm thinking maybe with the triggers? could share w/ elevator manual)
		
		// Separate "enable climbing mode" and "climb 12 inches"
		
		// Right joystick - press down starts "OperatorElevatorControl" which reads joystick power to set power %
		// Left joystick - same for Arm
	
	}
	
	public static double getDriverAxis(int axis) {
		return driver.getRawAxis(axis);
	}
	
}
