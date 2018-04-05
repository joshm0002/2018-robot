package com.techhounds.auton.paths;

import com.techhounds.auton.drive.DriveStraight;
import com.techhounds.auton.drive.TurnToAngleGyro;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleCross extends CommandGroup {

    public RightScaleCross() {
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	// drive across
    	addSequential(new DriveStraight(200, 0.7), 8);
    	addSequential(new DriveStraight(18, 0.4), 2);
    	addSequential(new TurnToAngleGyro(85), 3);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 4));
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_MID), 4));
    	addSequential(new DriveStraight(184, 0.6), 8);
    	
    	// put in scale
    	addSequential(new DriveStraight(30, 0.4), 2);
    	addSequential(new TurnToAngleGyro(-30), 2);
    	addSequential(new DriveStraight(12, 0.4), 2);
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	
    	// align to second cube
    	addSequential(new TurnToAngleGyro(-160), 2);
    }
}
