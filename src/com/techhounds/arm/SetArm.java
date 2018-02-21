package com.techhounds.arm;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class SetArm extends Command {
	
	private final boolean open;
	
    public SetArm(boolean open) {
    	requires(Robot.arm);
    	this.open = open;
    }
    
    protected void initialize() {
    	Robot.arm.setArm(open);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
