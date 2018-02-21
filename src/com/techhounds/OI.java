/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import com.techhounds.commands.Collector;
import com.techhounds.commands.SetTiltPower;
import com.techhounds.commands.ToggleTransmission;

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
		Button select = new JoystickButton(driver, 7);
		select.whenPressed(new ToggleTransmission());
		
		Button bB = new JoystickButton(driver, 2);
		bB.toggleWhenPressed(new Collector(1));
		
		Button bX = new JoystickButton(driver, 3);
		bX.toggleWhenPressed(new Collector(-1));
		
		Button bY = new JoystickButton(driver, 4);
		bY.toggleWhenPressed(new SetTiltPower(-0.5));
		
		Button bA = new JoystickButton(driver, 1);
		bA.toggleWhenPressed(new SetTiltPower(0.5));
	}
	
	/**
	 * Binds triggers/buttons to commands for the operator
	 */
	public static void setupOperator() {
		Button bA = new JoystickButton(operator, 1);
		// Set elevator to DOWN position
		
		Button bB = new JoystickButton(operator, 2);
		// Set elevator to SWITCH position
		
		Button bX = new JoystickButton(operator, 3);
		// Actuate collector arms
		
		Button bY = new JoystickButton(operator, 4);
		// Set elevator to SCALE position
		
		Button LB = new JoystickButton(operator, 5);
		// Intake IN
		
		Button RB = new JoystickButton(operator, 6);
		// Intake OUT
		
		Button select = new JoystickButton(operator, 7);
		// Flip out climbing hook
		
		Button start = new JoystickButton(operator, 8);
		// Enable climbing mode, climb 12"
		
		// Set arm position (UP/DOWN/45)
		
		// Manual arm position ??
		
		// Manual elevator control (I'm thinking maybe with the triggers?)
		
		// Manual climber control (I'm thinking maybe with the triggers? could share w/ elevator manual)
		
		// Separate "enable climbing mode" and "climb 12 inches"
	
	}
	
	public static double getDriverAxis(int axis) {
		return driver.getRawAxis(axis);
	}
	
}
