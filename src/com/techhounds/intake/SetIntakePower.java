package com.techhounds.intake;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakePower extends Command {
	
	private final double power;

	public SetIntakePower(double power) {
    	requires(Robot.intake);
    	this.power = power;
    }

    protected void initialize() {}
    
    public void execute() {
    	Robot.intake.setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }
}