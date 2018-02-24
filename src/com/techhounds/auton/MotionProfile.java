package com.techhounds.auton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * This class represents a motion profile to be loaded into
 * a MotionProfileExecutor command.
 * 
 * FIXME right now, it loads all of the profiles into Lists of TrajectoryPoint
 * objects - this will use lots of memory, but will likely make it very fast
 * to push points to the buffer. If necessary, getPoints() can instead read
 * the files on-the-fly (i.e. when the method is actually called) and construct
 * the points then - maybe we could even implement Iterator?
 * 
 * Also, it won't necessarily load all the data right when the program loads,
 * but rather when the class is 'Initialized' sometime later
 * 
 * Alternatives: Construct arrayList of objs only when getPoints is called for that one
 * (but still cache the ArrayList), preload double array but don't construct yet; serialize either double array
 * or arraylist to file and read that into memory; preload double array but let command handle
 * constructing the objects
 * 
 * TODO we need a spec for the CSV file format - current setup:
 * column number - data
 * 0 - position (rotations)
 * 1 - velocity (rot/s)
 * 2 - time interval (ms)
 * 
 * Of course, we'll need to at least duplicate 0 & 1 so we can have both right & left
 * side in a single file.
 */
public enum MotionProfile {
		
	Test("test.csv"),
	CenterToLeftSwitch("centerToLeftSwitch.csv");
	
	private List<TrajectoryPointPair> points;
	
	MotionProfile(String filename) {
		points = new ArrayList<>();
		
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
			buff.readLine();
			int lines = Integer.parseInt(buff.readLine());
			
			TrajectoryPoint[] left = new TrajectoryPoint[lines];
			TrajectoryPoint[] right = new TrajectoryPoint[lines];

			for (int i = 0; i < lines; i++) {
				left[i] = new TrajectoryPoint();
				
				String[] line = buff.readLine().split(",");
				left[i].position = Double.parseDouble(line[5-1]);
				left[i].velocity = Double.parseDouble(line[6-1]);
			}
			
			for (int i = 0; i < lines; i++) {
				right[i] = new TrajectoryPoint();
				
				String[] line = buff.readLine().split(",");
				right[i].position = Double.parseDouble(line[5-1]);
				right[i].velocity = Double.parseDouble(line[6-1]);
			}
			
			for (int i = 0; i < lines; i++) {
				points.add(new TrajectoryPointPair(left[i], right[i]));
			}		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: read file, push points to right/left points
		
		//for each line in file
		
//			TrajectoryPoint point = new TrajectoryPoint();
		
//			double positionRot = profilePoints[i][0];
//			double velocityRPM = profilePoints[i][1];
//			
//			//need to manipulate the numbers below in order to convert to proper units
//			point.position = positionRot * 1024;
//			point.velocity = velocityRPM * 1024 / 600;
//			point.profileSlotSelect0 = 0;
//			point.profileSlotSelect1 = 0;
//			
//			TrajectoryDuration td = TrajectoryDuration.Trajectory_Duration_5ms;
//			point.timeDur = td.valueOf((int)profile[i][2]);
//			point.timeDur = td;
//			point.zeroPos = (i == 0);
//			point.isLastPoint = (i + 1 == profilePoints.length);
		
//			points.push(point);
	}
	
	public List<TrajectoryPointPair> getPoints() {
		return points;
	}
}
