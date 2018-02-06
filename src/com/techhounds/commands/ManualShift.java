package com.techhounds.commands;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShift extends Command {
	
	public enum State {
		High,
		Low,
		Toggle
	}
	
	State state;

    public ManualShift(State state) {
        requires(Robot.transmission);
        this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switch(state) {
    	case High:
    		Robot.transmission.shift(true);
    		break;
    	case Low:
    		Robot.transmission.shift(false);
    		break;
    	case Toggle:
    		Robot.transmission.toggle();
    		break;
    	default:
    		// Error
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
