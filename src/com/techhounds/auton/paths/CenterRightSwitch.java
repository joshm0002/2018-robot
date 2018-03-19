package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightSwitch extends CommandGroup {

    public CenterRightSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	
    	// drive in s pattern
    	addSequential(new DriveArc(60, 75, 0.35, 0.5), 3);
    	addSequential(new DriveArc(70, 45, 0.5, 0.30), 3);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back up
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	addSequential(new DriveStraight(-20, -0.4), 2);
    }
}
