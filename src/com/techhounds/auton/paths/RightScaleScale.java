package com.techhounds.auton.paths;

import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
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
    	addSequential(new DriveStraight(230, 0.75), 6);
    	addSequential(new DriveStraight(10, 0.5), 1);
    	addSequential(new DriveArc(50, 20, 0.45, 0.2), 2); // curve left
//    	addSequential(new TurnToAngleGyro(-45), 1.5);
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 1);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new TurnToAngleGyro(-155), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	
    	// grab second cube
    	addSequential(new DriveStraight(55, 0.5), 2);
    	addSequential(new CollectCube(35, -155), 3);
//    	
//    	// retry grab if we didn't get it
//    	addSequential(new RetryCollectCube(40), 3);
//    	addSequential(new RetryCollectCube(40), 3);
//    	
//    	// place in scale
    	addSequential(new TurnToAngleGyro(-135), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(-45, -0.4), 3);
    	addSequential(new TurnToAngleGyro(-60), 2);
    	addSequential(new DriveStraight(20, 0.3), 2);
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-24, -0.3), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
