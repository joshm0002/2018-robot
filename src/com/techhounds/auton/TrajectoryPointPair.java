package com.techhounds.auton;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * This class represents a pair of TrajectoryPoint (part of the
 * Talon Phoenix library) - one for the left side and one for
 * the right side of the drivetrain.
 */
public class TrajectoryPointPair {
	
	public final TrajectoryPoint right;
	public final TrajectoryPoint left;
	
	public TrajectoryPointPair(TrajectoryPoint right, TrajectoryPoint left) {
		this.right = right;
		this.left = left;
	}
}
