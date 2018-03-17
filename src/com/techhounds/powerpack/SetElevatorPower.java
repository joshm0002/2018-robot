package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorPower extends Command {

	private final double power;
	
    public SetElevatorPower(double power) {
    	requires(Robot.powerPack);
    	this.power = power;
    }

    protected void initialize() {
    	Robot.powerPack.setElevatorPower(power);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
