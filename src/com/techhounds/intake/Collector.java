package com.techhounds.intake;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Collector extends Command {
	
	double power;

	public Collector(double power) {
    	requires(Robot.intake);
    	this.power = power;
    }

    protected void initialize() {
    }
    
    public void execute() {
    	Robot.intake.setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }
}