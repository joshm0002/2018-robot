/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

/**
 *The/ RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Motors (CAN)
	public static final int DRIVE_LEFT_PRIMARY = 25;
	public static final int DRIVE_LEFT_SECONDARY = 24;
	public static final int DRIVE_RIGHT_PRIMARY = 10;
	public static final int DRIVE_RIGHT_SECONDARY = 11;
	public static final int INTAKE_LEFT = 22; // FIXME: find real port #s
	public static final int INTAKE_RIGHT = 15;
	public static final int POWER_PACK_PRIMARY = 3;
	public static final int POWER_PACK_SECONDARY = 4;
	public static final int POWER_PACK_TERTIARY = 5;
	public static final int POWER_PACK_QUATERNARY = 6;
//	public static final int POWER_PACK_TERTIARY;
	public static final int TILT = 13;
	
	// Pneumatics (PCM)
	public static final int DRIVE_TRANSMISSION = 0;
	public static final int ARMS = 2;
	public static final int WINCH_TRANSMISSION = 1;
	public static final int WINCH_BRAKE = 3;

	
	// Sensors (Analog)
	public static final int IR_DISTANCE = 0;
	
	// Sensors (Digital)	
	
	// Drivetrain
}