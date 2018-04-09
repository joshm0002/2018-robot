package com.techhounds.auton.profiling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * NOTE: the profile files are saved in feet and radians, but we convert this
 * to inches and degrees (this is what TrajectoryPoint stores it as).
 */
public class MotionProfile {
	
	public static final double TIMESTEP_SECS = 0.01;
	private BufferedReader reader;
	
	public MotionProfile(String filename) {
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (Throwable t) {
			System.out.println("Error Opening Motion Profile " + filename);
			reader = null;
		}
	}
	
	public List<TrajectoryPoint> getTrajectoryPoints() {
		List<TrajectoryPoint> points = new ArrayList<>();
		
		if (reader == null) {
			return points; //empty arraylist
		}
		
		try {
			int lines = Integer.parseInt(reader.readLine().split(",")[1].trim());
			
			for (int i = 0; i < lines; i++) {
				String[] vals = reader.readLine().split(",");
				points.add(
					new TrajectoryPoint(
						Double.parseDouble(vals[0].trim()) * 12,
						Double.parseDouble(vals[1].trim()) * 12,
						Double.parseDouble(vals[2].trim()) * 12,
						Math.toDegrees(Double.parseDouble(vals[3].trim())),
						Math.toDegrees(Double.parseDouble(vals[4].trim())),
						Math.toDegrees(Double.parseDouble(vals[5].trim()))
					)
				);
			}
		} catch (Throwable t) {
			System.out.println("Error Loading Motion Profile");
			return points; //possibly incomplete, but better than nothing?
		}
		
		return points;
	}
	
	public class TrajectoryPoint {
		public final double linearDistance, linearVelocity, linearAcceleration,
							angularDistance, angularVelocity, angularAcceleration;
		
		public TrajectoryPoint(double linearDistance, double linearVelocity, double linearAcceleration,
							double angularDistance, double angularVelocity, double angularAcceleration) {
			this.linearDistance = linearDistance;
			this.linearVelocity = linearVelocity;
			this.linearAcceleration = linearAcceleration;
			this.angularDistance = angularDistance;
			this.angularVelocity = angularVelocity;
			this.angularAcceleration = angularAcceleration;
		}
	}
}
