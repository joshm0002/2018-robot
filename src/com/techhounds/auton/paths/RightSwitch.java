package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnByAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
    	// set to 45 & switch
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	
    	// drive up
    	addSequential(new DriveStraight(140, 0.75), 4);
    	addSequential(new TurnByAngleGyro(-90), 2);
    	addSequential(new DriveStraight(20, 0.5), 2);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back up
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
