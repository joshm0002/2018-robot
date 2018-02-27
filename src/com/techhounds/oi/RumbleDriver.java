package com.techhounds.oi;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleDriver extends Command {
	
	private final Timer timer = new Timer();
	private final double time;
	
	public RumbleDriver() {
		this(0);
	}

    public RumbleDriver(double timeSecs) {
    	this.time = timeSecs;
    }

    protected void initialize() {
    	timer.start();
    }

    protected void execute() {
    	OI.rumbleDriver(true);
    }

    protected boolean isFinished() {
        return time == 0 || timer.hasPeriodPassed(time);
    }

    protected void end() {
    	OI.rumbleDriver(false);
    }

    protected void interrupted() {
    	end();
    }
}
