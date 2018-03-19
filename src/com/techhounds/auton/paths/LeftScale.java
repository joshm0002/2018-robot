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
public class LeftScale extends CommandGroup {

    public LeftScale() {
    	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5));

    	// drive up & curve
    	addSequential(new DriveStraight(230, 0.75), 6);
    	addSequential(new DriveArc(10, 30, 0.2, 0.4), 2); // curve left
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 1);
    	
    	// back off and reset
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 3);
    }
}
