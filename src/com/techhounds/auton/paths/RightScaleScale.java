package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnByAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightScaleScale extends CommandGroup {

    public RightScaleScale() {
    	// do scale
//    	addSequential(new RightScale());
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 2.5));

    	// drive up & curve
    	addSequential(new DriveStraight(230, 0.6), 6);
    	addSequential(new DriveArc(30, 10, 0.4, 0.2), 2); // curve left
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE)); //TODO down?
//    	addSequential(new WaitCommand(1));
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off and reset
    	addSequential(new DriveArc(-20), 1);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	
    	// END RIGHT SCALE
    	
    	addSequential(new TurnByAngleGyro(-90), 2);
//    	addParallel(new GrabCube(), 3);
    	addParallel(new SetIntakePower(1), 3);
    	addSequential(new DriveStraight(24, 0.4), 3);
    	
    	// place in scale
    	addSequential(new DriveStraight(-24, 0.4), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new TurnByAngleGyro(90), 2);
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveStraight(20, 0.4), 2);
    	addSequential(new SetIntakePower(-0.75), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-24, -0.4), 2);
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
