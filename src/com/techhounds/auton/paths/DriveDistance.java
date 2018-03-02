package com.techhounds.auton.paths;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveDistance extends TimedCommand {
	
	private final double target;
	private double initial;

    public DriveDistance(double inches, double timeout) {
    	super(timeout);
    	requires(Robot.drivetrain);
    	this.target = inches;
    }

    protected void initialize() {
    	initial = Robot.drivetrain.getScaledAverageDistance();
    }

    // TODO: use Constants for max/min
    protected void execute() {
    	if (target > 0) {
    	Robot.drivetrain.setPower(0.4 * 0.88, 0.4);
    	} else {
    		Robot.drivetrain.setPower(-0.4 * 0.88, -0.4);
    	}
    }

    protected boolean isFinished() {
    	if (target > 0) { //drive forward
    		return (Robot.drivetrain.getScaledAverageDistance() - initial) > target;
    	} else { //drive backwards
    		return (Robot.drivetrain.getScaledAverageDistance() - initial) < target;
    	}
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
