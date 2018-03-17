package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleElevatorLimits extends Command {
	
	/**
	 * Toggles enabling/disabling elevator limit switches
	 */
    public ToggleElevatorLimits() {
    }

    protected void initialize() {
    	Robot.powerPack.setOverrideLimits(!Robot.powerPack.getOverrideLimits());
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
