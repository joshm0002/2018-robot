package com.techhounds.commands.auton;

import java.io.File;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionProfile extends Command {
	
	public enum Profile {
		
		Test("test.csv");
		
		private final String filename;
		
		Profile(String filename) {
			this.filename = filename;
		}
		
		public File getFile() {
			return new File(filename);
		}
	}
	
	private int maxAccel, distance;
	private long startTime;

	/**
	 * 
	 * @param profile
	 * @param maxAccel in milliseconds
	 * @param distance in meters
	 */
    public MotionProfile(Profile profile, int maxAccel, int distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		this.maxAccel = maxAccel;
    		this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		//constants from https://www.chiefdelphi.com/media/papers/3107
    		double t = Math.sqrt((2*Math.PI*distance)/maxAccel);
    		double k1 = (2*Math.PI)/t;
    		double k2 = maxAccel/k1;
    		// time passed since inizialize
    		long timePassed = startTime - System.currentTimeMillis();
    		// velocity calculation
    		double powerRight = k2*(1-Math.sin(k1*timePassed));
    		double powerLeft = powerRight;
    		Robot.drivetrain.setMotors(powerRight, powerLeft);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		// constants from https://www.chiefdelphi.com/media/papers/3107
    		double t = Math.sqrt((2*Math.PI*distance)/maxAccel);
		double k1 = (2*Math.PI)/t;
		double k2 = maxAccel/k1;
		double k3 = 1/k1;
		// time passed sinze inizialize
		long timePassed = startTime - System.currentTimeMillis();
		// displacement calculation
    		double distance_travelled = k2*(timePassed-k3*Math.sin(k1*timePassed));
    		
        return distance_travelled >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
