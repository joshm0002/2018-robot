package com.techhounds.auton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.techhounds.Constants;

/**
 * This class represents a motion profile to be loaded into
 * a MotionProfileExecutor command.
 */
public enum MotionProfile {
			
	Test("Test.csv");
	
	public final String filename;
	MotionProfile(String filename) {
		this.filename = filename;
	}
	
	public TrajectoryPointSequence getPoints() {
		TrajectoryPointSequence points = new TrajectoryPointSequence();

		try {

			BufferedReader file = new BufferedReader(new FileReader(new File(Constants.PROFILE_PATH + filename)));
			
			String line;
			while ((line = file.readLine()) != null) {
				String[] fields = line.split(",");
				
				if (fields.length != 4) continue;
				
				TrajectoryPoint right = makeTrajectoryPoint(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]));
				TrajectoryPoint left = makeTrajectoryPoint(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
				
				points.addPointPair(left, right);
			}		
			
			file.close();
		} catch (IOException e) {
			System.err.println("Failed to Load Profile at: " + filename);
		}
		
		if (!points.rightPoints.isEmpty()) {
			points.rightPoints.get(0).zeroPos = true;
			points.rightPoints.get(0).isLastPoint = true;
		}
		
		if (!points.leftPoints.isEmpty()) {
			points.leftPoints.get(0).zeroPos = true;
			points.leftPoints.get(0).isLastPoint = true;
		}
		
		return points;
	}
	
	public static TrajectoryPoint makeTrajectoryPoint(double position, double velocity) {
		TrajectoryPoint point = new TrajectoryPoint();
				
		//I think the 'recorder' saves them in talon units (encoder counts per 100 ms) so no conversion necessary
		point.position = position;
		point.velocity = velocity;
		point.profileSlotSelect0 = 0;
		point.profileSlotSelect1 = 0;
		point.timeDur = TrajectoryDuration.Trajectory_Duration_5ms;
		point.zeroPos = false;
		point.isLastPoint = false;
		
		return point;
	}
}
