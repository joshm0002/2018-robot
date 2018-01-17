package com.techhounds.commands;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shift extends Command {
	
	private boolean state;

	/**
	 * Shift the transmission
	 * 
	 * @param highGear True for High Gear, False for Low Gear
	 */
    public Shift(boolean highGear) {
        requires(Robot.transmission);
        state = highGear;
    }

    protected void initialize() {
    	Robot.transmission.shift(state);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
