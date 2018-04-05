package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.drive.DriveStraight;
import com.techhounds.auton.drive.TurnToAngleGyro;
import com.techhounds.auton.util.DelayedCommand;
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
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1.5));
    	
    	// drive in s pattern
    	addSequential(new DriveStraight(15, 0.4), 1);
    	addSequential(new TurnToAngleGyro(25), 0.75);
    	addSequential(new DriveStraight(65, 0.6), 3);
    	addSequential(new DriveStraight(20, 0.4), 1);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back up to starting position
    	addSequential(new DriveStraight(-10, -0.4), 1);
    	addSequential(new TurnToAngleGyro(25), 0.75);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-40, -0.6), 3);
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	
    	// grab another one
    	addSequential(new TurnToAngleGyro(0), 0.75);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraight(40, 0.5), 1);
    	addSequential(new DriveStraight(10, 0.3), 0.5);
    	
    	// line up to switch again
    	addSequential(new DriveStraight(-30, -0.6), 2);
    	addSequential(new TurnToAngleGyro(25), 0.75);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 0.5));
    	addSequential(new DriveStraight(50, 0.6), 3);
    	addSequential(new DriveStraight(30, 0.4), 2);
    	addSequential(new TurnToAngleGyro(0), 0.5);
    	
    	// place it baby
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// triple threat
    	addSequential(new DriveStraight(-35, -0.4), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	addSequential(new TurnToAngleGyro(-45), 1);
    	
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraight(35, 0.5), 1);
    	addSequential(new DriveStraight(-50, -0.4), 2);
    }
}
