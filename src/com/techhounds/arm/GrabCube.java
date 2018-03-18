package com.techhounds.arm;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabCube extends Command {

    public GrabCube() {
    	requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.setArm(true);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return Robot.intake.isCubeDetected();
    }

    protected void end() {
    	Robot.arm.setArm(false);
    }

    protected void interrupted() {
    	end();
    }
}
