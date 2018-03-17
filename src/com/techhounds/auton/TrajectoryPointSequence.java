package com.techhounds.auton;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * This class represents a pair of TrajectoryPoint (part of the
 * Talon Phoenix library) - one for the left side and one for
 * the right side of the drivetrain.
 */
public class TrajectoryPointSequence {
	
	public final List<TrajectoryPoint> leftPoints = new ArrayList<>();
	public final List<TrajectoryPoint> rightPoints = new ArrayList<>();
	
	public void addPointPair(TrajectoryPoint left, TrajectoryPoint right) {
		leftPoints.add(left);
		rightPoints.add(right);
	}
	
}
