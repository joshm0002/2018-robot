package com.techhounds.auton.profiling;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionProfileCommand extends Command {
	
	private final MotionProfile motionProfile;

    public MotionProfileCommand(MotionProfile profile) {
    	requires(Robot.drivetrain);
    	motionProfile = profile;
    }

    protected void initialize() {
    	Robot.drivetrain.mpe.setProfile(motionProfile);
    	Robot.drivetrain.mpe.enable();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return !Robot.drivetrain.mpe.isEnabled();
    }

    protected void end() {
    	Robot.drivetrain.mpe.disable();
    }

    protected void interrupted() {
    	end();
    }
}
