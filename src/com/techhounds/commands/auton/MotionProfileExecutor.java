package com.techhounds.commands.auton;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command uploads a motion profile to the top buffer
 * and begins executing it once at least a few points have
 * been pushed to the TalonSRX. 
 */
public class MotionProfileExecutor extends Command {
	
	// BEGIN MOTION PROFILE CLASS
	
	private final double[][] profilePoints; // TODO: left & right points

    public MotionProfileExecutor(MotionProfile profile) {
    	this(profile.getPoints());
    }
    
    public MotionProfileExecutor(double[][] points) {
    	profilePoints = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetProfile();
    	Robot.drivetrain.setMotionProfile(SetValueMotionProfile.Disable);
    	
    	// TODO: will this hang us too much?
    	
    	TrajectoryPoint point = new TrajectoryPoint();
    	
    	for(int i = 0; i < profilePoints.length; i++) {
    		double positionRot = profilePoints[i][0];
    		double velocityRPM = profilePoints[i][1];
    		
    		//need to manipulate the numbers below in order to convert to proper units
    		point.position = positionRot * 1024;
    		point.velocity = velocityRPM * 1024 / 600;
    		point.profileSlotSelect0 = 0;
    		point.profileSlotSelect1 = 0;
    		
    		TrajectoryDuration td = TrajectoryDuration.Trajectory_Duration_5ms;
//    		point.timeDur = td.valueOf((int)profile[i][2]);
    		point.timeDur = td;
    		point.zeroPos = (i == 0);
    		point.isLastPoint = (i + 1 == profilePoints.length);
    		
    		Robot.drivetrain.pushLeftPoint(point);   //TODO: push right points  		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(getStatus().btmBufferCnt < 10) return;
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
