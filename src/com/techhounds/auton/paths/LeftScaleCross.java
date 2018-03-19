package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleCross extends CommandGroup {

    public LeftScaleCross() {
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	// drive across
    	addSequential(new DriveStraight(218, 0.5), 8);
    	addSequential(new TurnToAngleGyro(-85), 3);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 4));
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_MID), 4));
    	addSequential(new DriveStraight(184, 0.5), 8);
    	
    	// put in scale
    	addSequential(new TurnToAngleGyro(10), 2);
    	addSequential(new DriveStraight(18, 0.4), 2);
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-20, -0.4));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
