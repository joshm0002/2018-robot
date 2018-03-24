package com.techhounds.drivetrain;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetDriveDirection extends Command {
	
	private final boolean direction;

    public SetDriveDirection(boolean direction) {
    	this.direction = direction;
    }

    protected void initialize() {
    	OI.driveDirection = direction;
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
