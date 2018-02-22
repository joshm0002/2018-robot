package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClimberPower extends Command {

	private final double power;
	
    public SetClimberPower(double power) {
    	requires(Robot.powerPack);
    	this.power = power;
    }

    protected void initialize() {
    	Robot.powerPack.setTransmission(false);
    	Robot.powerPack.setBrake(false);
    	Robot.powerPack.setWinchPower(power);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
