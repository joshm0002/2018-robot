package com.techhounds.auton.paths;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveDistance extends Command {
	
	private final double rightTarget;
	private final double leftTarget;
	private double rightInitial;
	private double leftInitial;
	private final double rightPower;
	private final double leftPower;
	
	public DriveDistance(double inches) {
		this(inches, inches, 0.4 * 0.88, 0.4);
	}

    public DriveDistance(double rightInches, double leftInches, double rightPower, double leftPower) {
    	requires(Robot.drivetrain);
    	this.rightTarget = rightInches;
    	this.leftTarget = leftInches;
    	this.rightPower = rightPower;
    	this.leftPower = leftPower;
    }

    protected void initialize() {
    	rightInitial = Robot.drivetrain.getScaledRightDistance();
    	leftInitial = Robot.drivetrain.getScaledLeftDistance();
    }

    protected void execute() {
    	double setRight = rightPower;
    	double setLeft = leftPower;
    	
    	if(isRightFinished()) {
    		setRight = 0;
    	}
    	
    	if(isLeftFinished()) {
    		setLeft = 0;
    	}
    	
    	Robot.drivetrain.setPower(setRight, setLeft);
    }

    protected boolean isFinished() {
    	return isRightFinished() && isLeftFinished();
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
    
    private boolean isRightFinished() {
    	return Math.abs(Robot.drivetrain.getScaledRightDistance() - rightInitial) > Math.abs(rightTarget);
    }
    
    private boolean isLeftFinished() {
    	return Math.abs(Robot.drivetrain.getScaledLeftDistance() - leftInitial) > Math.abs(leftTarget);
    }
}
