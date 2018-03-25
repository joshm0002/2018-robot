package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightSwitchDouble extends CommandGroup {

    public CenterRightSwitchDouble() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	
    	// drive in s pattern
    	addSequential(new DriveArc(60, 75, 0.35, 0.5), 3);
    	addSequential(new DriveArc(70, 45, 0.5, 0.30), 3);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back up to starting position
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
    	addSequential(new DriveArc(-70, -45, -0.5, -0.30), 3);
    	addSequential(new DriveArc(-60, -75, -0.35, -0.5), 3);
    	
    	// drive forward and grab cube
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraight(70, 0.4), 3);
    	
    	// back up to starting position
    	addSequential(new DriveStraight(-70, -0.6), 3);
    	
    	// place second cube
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
       	addSequential(new DriveArc(60, 75, 0.35, 0.5), 3);
    	addSequential(new DriveArc(70, 45, 0.5, 0.30), 3);
    }
}
