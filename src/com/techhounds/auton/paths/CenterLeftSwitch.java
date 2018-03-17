package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterLeftSwitch extends CommandGroup {

    public CenterLeftSwitch() {
    	// set to 45 & switch
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	
    	// drive in s pattern
    	addSequential(new DriveArc(70, 50, 0.5, 0.35), 3);
    	addSequential(new DriveArc(50, 70, 0.35, 0.5), 3);
    	
    	// eject cube
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new WaitCommand(1));
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back up
    	addSequential(new DriveArc(-20), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
