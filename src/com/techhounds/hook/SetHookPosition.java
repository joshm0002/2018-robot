package com.techhounds.hook;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetHookPosition extends Command {

	public final boolean up;
	
    public SetHookPosition(boolean up) {
    	requires(Robot.hook);
    	this.up = up;
    }

    protected void initialize() {
    	Robot.hook.setPosition(up);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
