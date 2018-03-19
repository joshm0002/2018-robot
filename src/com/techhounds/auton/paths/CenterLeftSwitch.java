package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftSwitch extends CommandGroup {

    public CenterLeftSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	
    	// drive in s pattern
    	addSequential(new DriveArc(70, 50, 0.5, 0.35), 3);
    	addSequential(new DriveArc(40, 70, 0.3, 0.5), 3);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back up
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	addSequential(new DriveStraight(-20, -0.4), 2);
    }
}
