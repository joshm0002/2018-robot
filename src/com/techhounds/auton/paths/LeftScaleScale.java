package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.drive.DriveArc;
import com.techhounds.auton.drive.DriveStraight;
import com.techhounds.auton.drive.DriveStraightUntilDetected;
import com.techhounds.auton.drive.TurnByAngleGyro;
import com.techhounds.auton.drive.TurnToAngleGyro;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.IntakeUntilDetected;
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
public class LeftScaleScale extends CommandGroup {

    public LeftScaleScale() {
    	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5));

    	// drive up & curve
    	addSequential(new DriveStraight(230, 0.6), 6);
    	addSequential(new DriveStraight(10, 0.4), 1);
    	addSequential(new DriveArc(10, 30, 0.2, 0.4), 2); // curve right
//    	addSequential(new TurnToAngleGyro(-45), 1.5);
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.4), 1);
    	
    	// back off and reset
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new DriveStraight(-20, -0.35), 2);
    	
    	// END RIGHT SCALE
    	
    	addSequential(new TurnToAngleGyro(130), 2);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraightUntilDetected(75, 0.4), 3);
    	addSequential(new WaitCommand(0.5));
    	
    	// place in scale
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(-65, -0.5), 3);
    	addSequential(new TurnByAngleGyro(-65), 2);
    	addSequential(new DriveStraight(20, 0.3), 2);
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-24, -0.3), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
