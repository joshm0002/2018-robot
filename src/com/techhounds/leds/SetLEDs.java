package com.techhounds.leds;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SetLEDs extends TimedCommand {
	
	private final int red, green, blue;
	
    public SetLEDs(int red, int green, int blue) {
    	this(red, green, blue, 0);
    }

    public SetLEDs(int red, int green, int blue, double time) {
    	super(time);
    	requires(Robot.leds);
    	this.red = red;
    	this.green = green;
    	this.blue = blue;
    }

    protected void initialize() {
    	Robot.leds.set(red, green, blue);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return super.isFinished();
    }

    protected void end() {}

    protected void interrupted() {}
}
