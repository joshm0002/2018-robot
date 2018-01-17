/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public final XboxController driver;
	public final XboxController operator;
	
	public OI() {
		driver = new XboxController(0);
		operator = new XboxController(1);
		
		//Set up button/triggers here
		//Be careful - if the commands require a subsystem that hasn't been
		//initialized yet we could be in trouble
	}
	
}
