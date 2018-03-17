package com.techhounds.auton.profiling;

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
    	requires(Robot.drivetrain);
    	this.profile = profile;
    }

    protected void initialize() {
    	Robot.drivetrain.resetProfile();
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Disable);
    	
    	Robot.drivetrain.uploadMotionProfile(profile.getPoints());
    	
    	System.out.println("Starting Profile: " + profile.toString());
    }

    protected void execute() {
    	// TODO: add Drivetrain.isProfileReady method for this?
//    	if (Robot.drivetrain.getLeftProfileStatus().btmBufferCnt < 10 ||
//    	    Robot.drivetrain.getLeftProfileStatus().isUnderrun ||
//    	    Robot.drivetrain.getRightProfileStatus().btmBufferCnt < 10 ||
//    	    Robot.drivetrain.getRightProfileStatus().isUnderrun) {
//    		return;
//    	}
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Enable);
    }

    /**
     * TODO: check all four conditions in an AND
     */
    protected boolean isFinished() {
    	MotionProfileStatus status = Robot.drivetrain.getLeftProfileStatus();
    	if (status.activePointValid && status.isLast) {
			return true;
    	}
    	status = Robot.drivetrain.getRightProfileStatus();
    	if (status.activePointValid && status.isLast) {
    		return true;
    	}
    	return false;
    }

    protected void end() {
    	System.out.println("Finished Profile " + profile.toString());
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Hold);
    }

    protected void interrupted() {
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Disable);
    }
}
