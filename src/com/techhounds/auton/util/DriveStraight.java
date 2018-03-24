package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO: add TalonPID-based distance command
 */
public class DriveStraight extends Command {
	
	private final double rightTarget;
	private final double leftTarget;
	private double rightInitial;
	private double leftInitial;
	private final double rightPower;
	private final double leftPower;
	
	// real robot
//	public static final double STRAIGHT_COEFFICIENT = 0.88;
	// practice bot FIXME
	
	private double initialAngle;
	
	public DriveStraight(double inches) {
		this(inches, inches > 0 ? 0.6 : -0.6);
	}

    public DriveStraight(double inches, double power) {
    	requires(Robot.drivetrain);
    	this.rightTarget = inches;
    	this.leftTarget = inches;
    	this.rightPower = power;
    	this.leftPower = power;
    }

    protected void initialize() {
    	rightInitial = Robot.drivetrain.getScaledRightDistance();
    	leftInitial = Robot.drivetrain.getScaledLeftDistance();
    	initialAngle = Robot.gyro.getRotation();
    }

    protected void execute() {
    	double setRight = rightPower;
    	double setLeft = leftPower;
    	
    	double angleError = Robot.gyro.getRotation() - initialAngle;
    	double angleP = (angleError / 50);
    	
    	// fix for driving backwards
    	if (rightPower < 0 && leftPower < 0) {
    		angleP *= -1;
    	}
    	
    	Robot.drivetrain.setPower(setRight * (1+angleP), setLeft * (1-angleP));
    }

    protected boolean isFinished() {
    	return isRightFinished() || isLeftFinished();
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
