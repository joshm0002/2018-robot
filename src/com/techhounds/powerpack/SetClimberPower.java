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
    	Robot.powerPack.setElevatorPosition(0); // TODO: uh oh, after lifting it the bottom pos tends to become ~-2000
    	Robot.powerPack.setClimberPower(power);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
