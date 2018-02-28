package com.techhounds.drivetrain;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlipDriveDirection extends Command {

    public FlipDriveDirection() {}

    protected void initialize() {
    	OI.driveDirection = !OI.driveDirection;
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
