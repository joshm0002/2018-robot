package com.techhounds.auton.profiling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.techhounds.Constants;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ProfileRecorder extends Command {
	
	private ProfileRecorderRunnable recorder;
	private Notifier notifier;
	private double period;

    public ProfileRecorder(double period) {
    	recorder = new ProfileRecorderRunnable();
    	notifier = new Notifier(recorder);
    	this.period = period;
    	SmartDashboard.putString("Profile Recorder File Name", "Test");
    }

    protected void initialize() {
    	recorder.saveInitialCounts();
    	notifier.startPeriodic(period);
    	System.out.println("Recording Profile");
    }

    protected void execute() {
    	// Intentionally blank: the Notifier records the data
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	notifier.stop();
    	recorder.writeToFile(SmartDashboard.getString("Profile Recorder File Name", "Test"));
    }

    protected void interrupted() {
    	end();
    }
    
    public class ProfileRecorderRunnable implements Runnable {
    	
    	/**
    	 * SCHEMA:
    	 * { RIGHT_DIST, RIGHT_VEL, LEFT_DIST, LEFT_VEL }
    	 */
    	List<double[]> data = new ArrayList<double[]>();
    	double rightInitialCounts = 0;
    	double leftInitialCounts = 0;

		@Override
		public synchronized void run() {
			double[] point = new double[4];
			point[0] = Robot.drivetrain.getRawRightDistance() - rightInitialCounts;
			point[1] = Robot.drivetrain.getRawRightVelocity();
			point[2] = Robot.drivetrain.getRawLeftDistance() - leftInitialCounts;
			point[3] = Robot.drivetrain.getRawLeftVelocity();
			data.add(point);
		}
		
		public void saveInitialCounts() {
			rightInitialCounts = Robot.drivetrain.getRawRightDistance();
			leftInitialCounts = Robot.drivetrain.getRawLeftDistance();
		}
		
		public synchronized void writeToFile(String filename) {
			try {
				BufferedWriter file = new BufferedWriter(new FileWriter(Constants.PROFILE_PATH + filename + ".csv"));
				
				file.write(filename + "\n" + data.size() + "\n");
				
				for (double[] point : data) {
					file.write("" + point[0] + ", " + point[1] + ", " + point[2] + ", " + point[3] + "\n");
				}
				
				file.flush();
				file.close();
				System.out.println("Successfully wrote Profile to " + filename);
			} catch (IOException e) {
				System.out.println("Failed to write Recorded Profile to " + filename);
			}
		}
    	
    }
}
