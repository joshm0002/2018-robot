package com.techhounds.powerpack.basic;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorBottom extends Command {

    public SetElevatorBottom() {
    	requires(Robot.powerPack);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.powerPack.setElevatorPower(0.5); // FIXME
    }

    protected boolean isFinished() {
        return Robot.powerPack.isBottomSwitchTripped();
    }

    protected void end() {}

    protected void interrupted() {}
}
