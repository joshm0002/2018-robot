package com.techhounds.subsystems;

import com.techhounds.RobotMap;
import com.techhounds.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private Spark motorLeft;
	private Spark motorRight;
	
	public Drivetrain() {
		motorLeft = new Spark(RobotMap.DRIVE_MOTOR_LEFT);
		motorRight = new Spark(RobotMap.DRIVE_MOTOR_RIGHT);
	}
	
	/**
	 * Set the drivetrain motor power.
	 * 
	 * @param right Decimal value [-1.0, 1] to set
	 * @param left Decimal value [-1.0, 1] to set
	 */
	public void setMotors(double right, double left) {
		left = constrain(left);
		right = constrain(right);
		motorLeft.set(left);
		motorRight.set(right);
	}
	
	/**
	 * Limits the given value to -1, 1
	 * 
	 * @param value
	 * @return
	 */
	private double constrain(double value) {
		return Math.min(Math.max(value, -1), 1);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
}

