package com.techhounds.hook;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetHookPower extends Command {

	private final double power;
	
    public SetHookPower(double power) {
    	requires(Robot.hook);
    	this.power = power;
    }

    protected void initialize() {
    	Robot.hook.setPower(power);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
