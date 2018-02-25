package com.techhounds.tilt;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTiltHold extends Command {

    public SetTiltHold() {
    	requires(Robot.tilt);
    }

    protected void initialize() {}

    protected void execute() {
    	if (Robot.tilt.getPosition() > -550) {
    		Robot.tilt.setPower(0.18);
    	} else {
    		Robot.tilt.setPower(0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
