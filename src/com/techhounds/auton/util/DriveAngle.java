package com.techhounds.auton.util;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAngle extends Command {

	private final double target;
	private double initial;
	private final double rightPower;
	private final double leftPower;
	private int inRange;
	
	public DriveAngle(double targetAngle) {
		this(targetAngle, targetAngle > 0 ? -0.4 : 0.4, targetAngle > 0 ? 0.4 : -0.4);
	}

	/**
	 * Rotate by a number of degrees (positive is clockwise/right)
	 * @param targetAngle
	 * @param rightPower
	 * @param leftPower
	 */
    public DriveAngle(double targetAngle, double rightPower, double leftPower) {
    	requires(Robot.drivetrain);
    	this.target = targetAngle;
    	this.rightPower = rightPower;
    	this.leftPower = leftPower;
    }

    protected void initialize() {
    	Robot.gyro.reset();
    	initial = Robot.gyro.getRotation();
    	inRange = 0;
    }

    protected void execute() {
    	double setRight = rightPower;
    	double setLeft = leftPower;
    	
    	double slowDown = Math.abs(target * 0.4);
    	double err = Math.abs(getError());
    	if (err < slowDown) {
    		double P = 0.25 * err / slowDown;
    		setRight *= P;
    		double minPower = 0.125;
    		if (setRight > -minPower && setRight < minPower) {
    			setRight = (setRight < 0) ? -minPower : minPower;
    		}
    		setLeft *= P;

    		if (setLeft > -minPower && setLeft < minPower) {
    			setLeft = (setLeft < 0) ? -minPower : minPower;
    		}
    	}
    	
    	if (err < 5) {
    		inRange++;
    	} else {
    		inRange = 0;
    	}
    	
    	Robot.drivetrain.setPower(setRight, setLeft);
    }

    protected boolean isFinished() {
    	return inRange >= 3;
 //   	return Math.abs(getError()) < 10;
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
    
    private double getError() {
    	return target - getChange();
    }
    
    private double getChange() {
    	return Robot.gyro.getRotation() - initial;
    }
}
