package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class RetryCollectCube extends ConditionalCommand {

	public RetryCollectCube(double distance) {
		super(new CollectCube(distance));
	}

	@Override
	protected boolean condition() {
		return Robot.intake.isCubeDetected();
	}

}
