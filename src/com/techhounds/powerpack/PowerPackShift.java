package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PowerPackShift extends Command {
	
	boolean elevator = true;

    public PowerPackShift(boolean elevator) {
        requires(Robot.powerPack);
        this.elevator = elevator;
    }
    
    public PowerPackShift(PowerPackState state){
    	requires(Robot.powerPack);
    	switch(state){
    	case elevator:
    		elevator = true;
    		break;
    	case climber:
    		elevator = false;
    		break;
    	default:
    		break;
    	}
    }
    
    public enum PowerPackState{
    	elevator, climber
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(elevator){
    		Robot.powerPack.toElevator();
    	}else if(!elevator){
    		Robot.powerPack.toClimber();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
