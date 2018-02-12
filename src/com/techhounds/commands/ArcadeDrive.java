package com.techhounds.commands;

import com.techhounds.OI;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {} //TODO configModePower

    /**
     * TODO: do we want to square/cube input?
     */
    protected void execute() {
    	double forward = -OI.getDriverAxis(1); //negatives are intentional
    	double turn = -OI.getDriverAxis(4);
    	
    	Robot.drivetrain.setPower(forward+turn, forward-turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
