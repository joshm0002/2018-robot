package com.techhounds.arm;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class SetArm extends Command {
	private boolean armClose;
    public SetArm(boolean armClose) {
    	requires(Robot.arm);
    	this.armClose = armClose;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(armClose) Robot.arm.closeArm();
    	else Robot.arm.openArm();
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
