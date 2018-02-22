package com.techhounds.hook;

import com.techhounds.OI;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverHookControl extends Command {

    public DriverHookControl() {
    	requires(Robot.hook);
    }

    protected void initialize() {}

    protected void execute() {
    	double rt = OI.getDriverAxis(4); // FIXME
    	double lt = OI.getDriverAxis(5);
    	
    	Robot.hook.setPower(rt - lt);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
