package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnByAngleGyro;
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
public class RightScaleScale extends CommandGroup {

    public RightScaleScale() {
    	// do scale
    	addSequential(new RightScale());
    	
    	addSequential(new TurnByAngleGyro(-90), 2);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraight(24, 0.4), 3);
    	
    	// place in scale
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(-24, 0.4), 2);
    	addSequential(new TurnByAngleGyro(90), 2);
    	addSequential(new DriveStraight(20, 0.4), 2);
    	addSequential(new SetIntakePower(-0.75), 1);
    	
    	// back off
    	addSequential(new DriveStraight(-24, -0.4), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addSequential(new SetElevatorPosition(ElevatorPosition.COLLECT), 2);
    }
}
