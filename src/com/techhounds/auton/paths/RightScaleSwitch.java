package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnByAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleSwitch extends CommandGroup {

    public RightScaleSwitch() {
    	// do scale
    	addSequential(new RightScale());
    	addSequential(new TurnByAngleGyro(-90), 2);
    	
    	// grab cube
    	addParallel(new GrabCube(), 3);
    	addParallel(new SetIntakePower(1), 3);
    	addSequential(new DriveStraight(24, 0.4), 3);
    	addSequential(new SetElevatorPosition(ElevatorPosition.SWITCH), 3);
    	
    	// place in switch
    	addSequential(new DriveStraight(12, 0.4), 2);
    	addSequential(new SetIntakePower(-0.75), 1);
    	
    	// back off
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    	addSequential(new DriveStraight(-24, -0.4), 2);
    }
}
