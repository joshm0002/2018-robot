package com.techhounds.intake;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilDetected extends Command {
	
	private int detectCounts = 0;

    public IntakeUntilDetected() {
    	requires(Robot.intake);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.intake.setPower(1);
    	if (Robot.intake.isCubeDetected()) {
    		detectCounts++;
    	}
    }

    protected boolean isFinished() {
        return detectCounts > 5;
    }

    protected void end() {
    	Robot.intake.setPower(0);
    }

    protected void interrupted() {
    	end();
    }
}
