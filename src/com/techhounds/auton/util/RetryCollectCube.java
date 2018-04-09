package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class RetryCollectCube extends ConditionalCommand {

	public RetryCollectCube(double distance, double angle) {
		super(new CollectCube(distance, angle));
	}

	@Override
	protected boolean condition() {
		return Robot.intake.isCubeDetected();
	}

}
