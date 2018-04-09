package com.techhounds.arm;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabCube extends Command {
	
	private int detectCounts = 0;

    public GrabCube() {
    	requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.setArm(true);
    }

    protected void execute() {
    	if (Robot.intake.isCubeDetected()) {
    		detectCounts++;
    	} else {
    		detectCounts = 0;
    	}
    }

    protected boolean isFinished() {
        return detectCounts > 5;
    }

    protected void end() {
    	Robot.arm.setArm(false);
    }

    protected void interrupted() {
    	end();
    }
}
