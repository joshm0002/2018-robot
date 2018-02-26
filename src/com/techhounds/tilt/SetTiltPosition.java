package com.techhounds.tilt;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: use RobotPreferences for range values
 */
public class SetTiltPosition extends Command {

    public enum TiltPosition { // FIXME non-cont. values
    	UP(Tilt.POS_UP),
    	MIDDLE(Tilt.POS_MID),
    	DOWN(Tilt.POS_DOWN);

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

    // TODO: Math.floorMod on the position?
    protected void execute() {
    	double position = Robot.tilt.getPosition();
    	double error = setpoint - position;
    	
    	// power needed to hold us at that setpoint
    	double holdPower;
    	if (setpoint > Tilt.POS_DOWN + 50) {
    		holdPower = (1 - ((setpoint - Tilt.POS_DOWN) / Tilt.POS_RANGE)) * 0.3;
    	} else {
    		holdPower = 0; //sit on the hardstops
    	}
    	
    	// error-proportion
    	double errorPower;
    	if (error > 15) {
    		errorPower = (1 - (error / Tilt.POS_RANGE)) * 0.75;
    	} else if (error < -15) {
    		errorPower = (error / Tilt.POS_RANGE) * -0.3;
    	} else {
    		errorPower = 0;
    	}
    	
    	Robot.tilt.setPower(holdPower + errorPower);
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
