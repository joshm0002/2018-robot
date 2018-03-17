package com.techhounds.compressor;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleCompressor extends Command {

    public ToggleCompressor() {}

    protected void initialize() {
    	boolean state = Robot.compressor.getClosedLoopControl();
    	Robot.compressor.setClosedLoopControl(!state);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
