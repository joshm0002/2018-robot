/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import com.techhounds.commands.Collector;
import com.techhounds.commands.ManualShift;
import com.techhounds.commands.ManualShift.State;
import com.techhounds.commands.SetTiltPower;

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
	
	public OI() {
		
		//Set up button/triggers here
		//Be careful - if the commands require a subsystem that hasn't been
		//initialized yet we could be in trouble
		
		//Overrides the default, which is auto-shifting
		
	}
	
	/**
	 * Binds triggers/buttons to commands for the driver
	 */
	public static void setupDriver() {
		Button select = new JoystickButton(driver, 7);
		select.whenPressed(new ManualShift(State.Toggle));
		
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
		
	}
	
	public static double getDriverAxis(int axis) {
		return driver.getRawAxis(axis);
	}
	
}
