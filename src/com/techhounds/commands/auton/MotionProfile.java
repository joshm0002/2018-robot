package com.techhounds.commands.auton;

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
		
		public double[][] getPoints() {
			// TODO: read file
			double[][] points = {
					{0, 0, 0}
			};
			return points;
		}
	}

    public MotionProfile(Profile profile) {
    	this(profile.getPoints());
    }
    
    public MotionProfile(double[][] points) {
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
