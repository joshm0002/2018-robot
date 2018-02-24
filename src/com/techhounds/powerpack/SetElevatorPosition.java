package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorPosition extends Command {
	
	public enum ElevatorPosition {
		COLLECT(0),
		SWITCH(200),
		SCALE(1000);
		
		public final double setpoint;
		private ElevatorPosition(double value) {
			setpoint = value;
		}
	}
	
	private final double setpoint;
	
	public SetElevatorPosition(ElevatorPosition position) {
		this(position.setpoint);
	}

    public SetElevatorPosition(double setpoint) {
    	requires(Robot.powerPack);
    	this.setpoint = setpoint;
    }

    protected void initialize() {
    	Robot.powerPack.setElevatorPosition(setpoint);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return Robot.powerPack.onTarget();
    }

    protected void end() {
    	// Don't need to do anything, because powerPack's default command is
    	// to hold position!
    }

    protected void interrupted() {}
}
