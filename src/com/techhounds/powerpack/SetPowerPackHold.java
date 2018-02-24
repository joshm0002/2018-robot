package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPowerPackHold extends Command {

    public SetPowerPackHold() {
    	requires(Robot.powerPack);
    }

    protected void initialize() {
    	Robot.powerPack.setBrake();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}