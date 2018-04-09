package com.techhounds.auton.profiling;

import java.util.List;

import com.techhounds.Robot;
import com.techhounds.auton.profiling.MotionProfile.TrajectoryPoint;

public class MotionProfileExecutor implements Runnable {
	
	// TODO: move these to drivetrain?
	// represents power percentage to reach speed in inch/s
	// e.g. 1 / max_speed (inch/sec)
	// such that 100% (or 1.0) * kV = max speed
	public static final double LEFT_kV = 0.015;
	public static final double RIGHT_kV = 0.015;
	// multiplied by # of degrees off angle to find power adjust
	public static final double GYRO_kP = 0.01;
	// multiplied by # of inches off we are
	public static final double POSITION_kP = 0.01;
	
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
			TrajectoryPoint currentPoint = currentProfile.remove(0);
			
			double rightPower = currentPoint.linearVelocity * RIGHT_kV;
			double leftPower = currentPoint.linearVelocity * LEFT_kV;
			
			// Gyro correction - purely positional (ignores velocity/acceleration)
			double currentHeading = Robot.gyro.getRotation();
			double headingError = currentHeading - currentPoint.angularDistance; //pos = angled right = reduce left power
			double headingModifier = headingError * GYRO_kP;
			
			rightPower += headingModifier;
			leftPower -= headingModifier;
			
			// Position correction
			double currentPosition = Robot.drivetrain.getScaledAverageDistance();
			double positionError = currentPosition - currentPoint.linearDistance; //pos = too far = reduce power
			double positionModifier = positionError * POSITION_kP;
			
			rightPower -= positionModifier;
			leftPower -= positionModifier;
			
			Robot.drivetrain.setPower(rightPower, leftPower);
		}
	}
}
