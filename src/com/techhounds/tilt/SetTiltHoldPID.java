package com.techhounds.tilt;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTiltHoldPID extends Command {

    public SetTiltHoldPID() {
    	requires(Robot.tilt);
    }

    protected void initialize() {
    	Robot.tilt.setPosition(Robot.tilt.getPosition());
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
