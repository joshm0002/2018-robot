package com.techhounds.auton;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command uploads a motion profile to the top buffer
 * and begins executing it once at least a few points have
 * been pushed to the TalonSRX. 
 */
public class MotionProfileExecutor extends Command {
	
	private final MotionProfile profile;
		
    public MotionProfileExecutor(MotionProfile profile) {
    	this.profile = profile;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetProfile();
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Disable);
    	
    	// TODO: will this hang us too much?
    	for(TrajectoryPointPair point : profile.getPoints()) {
    		Robot.drivetrain.pushPoint(point);
    	}
    	
    	System.out.println("Starting Profile: " + profile.toString());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(getStatus().btmBufferCnt < 10) return;
    	if (Robot.drivetrain.getLeftProfileStatus().btmBufferCnt < 10) return;
    	//if(getStatus().isUnderrun) return;
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Enable);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	MotionProfileStatus status = Robot.drivetrain.getLeftProfileStatus(); //TODO: check right status
    	if (status.activePointValid && status.isLast) {
			return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Hold);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Disable);
    }
}
