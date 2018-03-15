package com.techhounds.hook;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleHookPosition extends Command {

    public ToggleHookPosition() {
    	requires(Robot.hook);
    }

    protected void initialize() {
    	Robot.hook.setPosition(!Robot.hook.isUp());
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
