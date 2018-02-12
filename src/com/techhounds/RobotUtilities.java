package com.techhounds;

public class RobotUtilities {
	/**
	 * Limits the given value to -1, 1
	 * 
	 * @param value
	 * @return
	 */
	public static double constrain(double value) {
		return Math.min(Math.max(value, -1), 1);
	}
}
