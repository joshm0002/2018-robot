package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SetElevatorPosition extends TimedCommand {
	
	public enum ElevatorPosition {
		COLLECT(-10000),
		SWITCH(300000),
		SCALE(750000);
		
		public final double setpoint;
		private ElevatorPosition(double value) {
			setpoint = value;
		}
	}
	
	private final double setpoint;
	
	public SetElevatorPosition(ElevatorPosition position) {
		this(position.setpoint, 5);
	}
	
	public SetElevatorPosition(ElevatorPosition position, double timeout) {
		this(position.setpoint, timeout);
	}

    public SetElevatorPosition(double setpoint, double timeoutSecs) {
    	super(timeoutSecs);
    	requires(Robot.powerPack);
    	this.setpoint = setpoint;
    }

    protected void initialize() {
    	Robot.powerPack.setElevatorPosition(setpoint);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return Robot.powerPack.onTarget() || 
        	   (setpoint > 650000 && Robot.powerPack.isTopSwitchTripped()) ||
        	   (setpoint < 100000 && Robot.powerPack.isBottomSwitchTripped());
    }

    protected void end() {
    	Robot.powerPack.setBrake();
    }

    protected void interrupted() {
    	end();
    }
}
