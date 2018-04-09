package com.techhounds.auton.profiling;

import com.techhounds.auton.drive.DriveStraight;
import com.techhounds.auton.drive.TurnToAngleGyro;
import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.RetryCollectCube;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRightProfile extends CommandGroup {

    public RightScaleRightProfile() {
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5));

    	// drive to scale
    	addSequential(new MotionProfileCommand(new MotionProfile("/home/lvuser/right-rightscale.profile")), 5);
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 1);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
    	addSequential(new DriveStraight(-20, -0.35), 2);
    	
    	// grab second cube
    	addSequential(new TurnToAngleGyro(-135), 2);
    	addSequential(new MotionProfileCommand(new MotionProfile("/home/lvuser/rightscale-firstcube.profile")), 3);
    	addSequential(new CollectCube(24), 3);
    	addSequential(new DriveStraight(-24, -0.4), 2);
    	
    	// retry grab if we didn't get it
    	addSequential(new RetryCollectCube(24), 3);
    	addSequential(new DriveStraight(-24, -0.4), 2);
    	addSequential(new RetryCollectCube(24), 3);
    	addSequential(new DriveStraight(-24, -0.4), 2);
    }
}
