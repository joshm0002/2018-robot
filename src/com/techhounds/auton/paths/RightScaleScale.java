package com.techhounds.auton.paths;


import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.RetryCollectCube;
import com.techhounds.auton.util.TurnByAngleGyro;
import com.techhounds.auton.util.TurnToAngleGyro;
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
public class RightScaleScale extends CommandGroup {

    public RightScaleScale() {
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5));

    	// drive up & curve
    	addSequential(new DriveStraight(230, 0.6), 6);
    	addSequential(new DriveStraight(10, 0.4), 1);
    	addSequential(new DriveArc(30, 10, 0.4, 0.2), 2); // curve left
//    	addSequential(new TurnToAngleGyro(-45), 1.5);
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 1);
    	
    	// back off and reset
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new DriveStraight(-20, -0.35), 2);
    	
    	// END RIGHT SCALE
    	
    	// grab second cube
    	addSequential(new TurnToAngleGyro(-132), 2);
    	addSequential(new CollectCube(75), 3);
    	addSequential(new DriveStraight(-40, -0.4), 2);
    	
    	// retry grab if we didn't get it
    	addSequential(new RetryCollectCube(40), 3);
    	addSequential(new DriveStraight(-40, -0.4), 2);
    	addSequential(new RetryCollectCube(40), 3);
    	addSequential(new DriveStraight(-40, -0.4), 2);
    	
    	// place in scale
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(-45, -0.5), 3);
    	addSequential(new TurnByAngleGyro(65), 2);
    	addSequential(new DriveStraight(20, 0.3), 2);
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-24, -0.3), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
