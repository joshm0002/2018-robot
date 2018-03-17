package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorLimits extends Command {

	private final boolean overrideLimits;
	
    public SetElevatorLimits(boolean override) {
    	this.overrideLimits = override;
    }

    protected void initialize() {
    	Robot.powerPack.setOverrideLimits(overrideLimits);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
