package com.techhounds.commands;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleTransmission extends Command {

    public ToggleTransmission() {
    	requires(Robot.transmission);
    }

    protected void initialize() {
    	Robot.transmission.toggle();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
