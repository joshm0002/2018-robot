package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScale extends CommandGroup {

    public LeftScale() {
    	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 3));

    	// drive up & curve
    	addSequential(new DriveDistance(260, 260, 0.5, 0.5), 6);
    	addSequential(new DriveDistance(-20, 40, -0.2, 0.4), 1.5); // curve left
    	addSequential(new DriveDistance(10), 1);
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE)); //TODO down?
//    	addSequential(new WaitCommand(1));
    	addSequential(new SetIntakePower(-0.75), 1);
    	
    	// back off and reset
    	addSequential(new DriveDistance(-20), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
