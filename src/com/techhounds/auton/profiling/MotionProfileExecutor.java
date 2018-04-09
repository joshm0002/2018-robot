package com.techhounds.auton.profiling;

import java.util.List;

import com.techhounds.auton.profiling.MotionProfile.TrajectoryPoint;

public class MotionProfileExecutor implements Runnable {
	
	// TODO: move these to drivetrain?
	
	private boolean isEnabled = false;
	private List<TrajectoryPoint> currentProfile = null;
	
	public synchronized void enable() {
		isEnabled = true;
	}
	
	public synchronized void disable() {
		isEnabled = false;
	}
	
	public synchronized boolean isEnabled() {
		return isEnabled;
	}
	
	public synchronized void setProfile(MotionProfile profile) {
		currentProfile = profile.getTrajectoryPoints();
	}

	@Override
	public synchronized void run() {
		if (currentProfile == null || currentProfile.isEmpty()) {
			isEnabled = false;
		}
		
		// guaranteed currentProfile isn't null or empty, as this is synch'd
		if (isEnabled) {
			
		}
	}
}
