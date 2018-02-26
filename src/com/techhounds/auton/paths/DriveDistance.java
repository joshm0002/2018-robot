package com.techhounds.auton.paths;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveDistance extends TimedCommand {
	
	private final double inches;

    public DriveDistance(double inches, double timeout) {
    	super(timeout);
    	requires(Robot.drivetrain);
    	this.inches = inches;
    }

    protected void initialize() {
    	Robot.drivetrain.zeroEncoders();
    }

    // TODO: use Constants for max/min
    protected void execute() {
    	Robot.drivetrain.setPower(0.4, 0.4);
    }

    protected boolean isFinished() {
        return Robot.drivetrain.getScaledAverageDistance() > inches;
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
