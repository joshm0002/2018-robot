package com.techhounds.powerpack;

import com.techhounds.Robot;
import com.techhounds.powerpack.PowerPack.PowerPackState;

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
    	Robot.powerPack.setState(PowerPackState.CLIMBER);
    	Robot.powerPack.setWinchPower(power);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
