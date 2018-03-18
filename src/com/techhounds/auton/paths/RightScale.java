package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 2.5));

    	// drive up & curve
    	addSequential(new DriveStraight(230, 0.6), 6);
    	addSequential(new DriveArc(30, 10, 0.4, 0.2), 2); // curve left
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE)); //TODO down?
//    	addSequential(new WaitCommand(1));
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off and reset
    	addSequential(new DriveArc(-20), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
