package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnByAngleGyro;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

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
    	addSequential(new DriveStraight(-20, -0.35), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 3);
    	
    	// END RIGHT SCALE
    	
    	addSequential(new TurnToAngleGyro(115), 2);
    	addParallel(new GrabCube(), 4);
    	addParallel(new IntakeUntilDetected(), 4);
    	addSequential(new DriveStraight(85, 0.4), 4);
    	addSequential(new WaitCommand(0.5));
    	
    	// place in scale
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(-85, -0.5), 3);
    	addSequential(new TurnByAngleGyro(-55), 2);
    	addSequential(new DriveStraight(20, 0.4), 2);
    	addSequential(new SetIntakePower(-0.75), 1);
    	
//    	// back off
    	addSequential(new DriveStraight(-24, -0.4), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
