package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightUntilDetected;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterLeftSwitchDouble extends CommandGroup {

    public CenterLeftSwitchDouble() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1.5));
    	
    	// drive up to switch
    	addSequential(new DriveStraight(6, 0.4), 0.5);
    	addSequential(new TurnToAngleGyro(-30), 1); //too much?
    	addSequential(new DriveStraight(65, 0.6), 3);
    	addSequential(new DriveStraight(30, 0.4), 1);
    	addSequential(new TurnToAngleGyro(-10), 0.5);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// LAST CHECK
    	
    	// back up to starting position
    	addSequential(new DriveStraight(-10, -0.4), 1);
    	addSequential(new TurnToAngleGyro(-30), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-55, -0.6), 3);
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	
//    	// grab another one
    	addSequential(new TurnToAngleGyro(0), 1.5);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraightUntilDetected(40, 0.5), 1);
    	addSequential(new DriveStraightUntilDetected(10, 0.3), 0.5);
    	
    	// line up to switch again
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TurnToAngleGyro(-70), 1.5);
    	addSequential(new DriveStraight(50, 0.5), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SWITCH));
    	addSequential(new TurnToAngleGyro(0), 1);
    	addSequential(new DriveStraight(12, 0.4), 1);
    	
    	addSequential(new SetIntakePower(-0.5), 0.5);
    }
}
