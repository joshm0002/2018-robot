package com.techhounds.commands.vision;

import com.techhounds.subsystems.PullVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateUsingDriverVision extends Command {
	private PullVision vis = new PullVision();

    public RotateUsingDriverVision() {
        requires(vis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	new TurnByAngleGyro(vis.getTurnAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
