package com.techhounds.tilt;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Range: -610, -490, -400
 * TODO: use RobotPreferences for range values
 */
public class SetTiltPosition extends Command {

    public enum TiltPosition {
    	UP(-400),
    	MIDDLE(-490),
    	DOWN(-610);

    	public final double setpoint;
    	TiltPosition(double setpoint) {
    		this.setpoint = setpoint;
    	}
    }
	
    private double setpoint;
    
    public SetTiltPosition(TiltPosition position) {
    	this(position.setpoint);
    }

	public SetTiltPosition(double setpoint) {
    	this.setpoint = setpoint;
    	requires(Robot.tilt);
    }

    protected void initialize() {}

    // TODO: scale based on position - ie if going up, we can
    // slow down as we near our setpoint
    protected void execute() {
    	double position = Robot.tilt.getPosition();
    	double error = setpoint - position;
    	
    	if (setpoint > -550) { // going up
    		if (error > 15) { // need to move up
    			Robot.tilt.setPower(0.65);
    		} else if (error < -15) { //need to go down
    			Robot.tilt.setPower(-0.1);
    		}
    	} else { //going down
    		if (error < -15) { //need to go down
    			Robot.tilt.setPower(-0.15);
    		}
    	}
    }

    protected boolean isFinished() {
        return Math.abs(Robot.tilt.getPosition() - setpoint) < 15;
    }

    protected void end() {}

    protected void interrupted() {}
}
