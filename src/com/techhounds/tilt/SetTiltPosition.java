package com.techhounds.tilt;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * TODO: when we set it to UP, we'll want to maintain the
 * position (in case we get bumped and the arms get knocked
 * down) When we set it to DOWN, once we get near our setpoint we
 * want to cut power, so that our motors aren't stalling and
 * the arms are resting on the hard stops.
 */
public class SetTiltPosition extends Command {
	
    public enum TiltPosition {
    	UP(100), 
    	DOWN(0);

    	public final double setpoint;
    	TiltPosition(double setpoint) {
    		this.setpoint = setpoint;
    	}
    }
	
	private final double setpoint;
	
    public SetTiltPosition(TiltPosition tilt) {
    	this(tilt.setpoint);
    }

    public SetTiltPosition(double setpoint) {
        requires(Robot.tilt);
    	this.setpoint = setpoint;
    }

    protected void initialize() {
    	Robot.tilt.setPosition(setpoint);
    }

    protected void execute() {}

    /**
     * TODO: finish when we're on-target?
     */
    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
