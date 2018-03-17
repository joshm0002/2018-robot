package com.techhounds.oi;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleOperator extends Command {
	
	private final Timer timer = new Timer();
	private final double time;
	
	public RumbleOperator() {
		this(0);
	}

    public RumbleOperator(double timeSecs) {
    	this.time = timeSecs;
    }

    protected void initialize() {
    	timer.start();
    }

    protected void execute() {
    	OI.rumbleOperator(true);
    }

    protected boolean isFinished() {
        return time == 0 || timer.hasPeriodPassed(time);
    }

    protected void end() {
    	OI.rumbleOperator(false);
    }

    protected void interrupted() {
    	end();
    }
}
