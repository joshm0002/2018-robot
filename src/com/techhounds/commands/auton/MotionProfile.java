package com.techhounds.commands.auton;

/**
 * This class represents a motion profile to be loaded into
 * a MotionProfileExecutor command.
 *
 */
public enum MotionProfile {
	
	Test("test.csv");
	
	private final String filename;
	
	MotionProfile(String filename) {
		this.filename = filename;
	}
	
	public double[][] getPoints() {
		// TODO: read file
		double[][] points = {
				{0, 0, 0}
		};
		return points;
	}
}
