package com.techhounds.auton;

import java.util.List;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotionProfileUploader implements Runnable {
	
	private final TalonSRX talon;
	private List<TrajectoryPoint> points;
	private MotionProfileStatus status = new MotionProfileStatus();
	
	public MotionProfileUploader(TalonSRX talon) {
		this.talon = talon;
	}
	
	public synchronized void setPoints(List<TrajectoryPoint> points) {
		this.points = points;
	}

	@Override
	/**
	 * TODO: would it be fine to simply do all of this in MotionProfileExecutor's
	 * execute() method? Their docs recommend calling process..Buffer twice as often
	 * as your waypoints (in our case, 2.5 ms) but is that actually necessary?
	 */
	public synchronized void run() {
		talon.getMotionProfileStatus(status);
		while (points.size() > 0 && status.topBufferRem > 0) {
			talon.pushMotionProfileTrajectory(points.remove(0));
		}
		
		// TODO call this in loop while btmBufferCnt isn't at max (128)?
		talon.processMotionProfileBuffer();
	}

}
