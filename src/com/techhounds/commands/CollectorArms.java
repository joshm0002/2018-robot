package com.techhounds.commands;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectorArms extends Command {
	
	private boolean open;

    public CollectorArms(boolean open) {
        requires(Robot.arm);
        this.open = open;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(open)Robot.arm.openArm();
    	else if(!open)Robot.arm.closeArm();
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
