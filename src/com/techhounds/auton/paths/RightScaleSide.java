package com.techhounds.auton.paths;

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
public class RightScaleSide extends CommandGroup {

    public RightScaleSide() {
    	addSequential(new DriveStraight(275, 0.7), 5);
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new DriveStraight(30, 0.4), 2);
    	addSequential(new TurnToAngleGyro(-90), 2);
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	addSequential(new SetElevatorPosition(ElevatorPosition.SCALE), 5);
    	addSequential(new DriveStraight(25, 0.3), 2);
    	addSequential(new SetIntakePower(-0.6), 0.5);
    	addSequential(new DriveStraight(-15, -0.3), 2);
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    	addSequential(new TurnToAngleGyro(0), 3);
    }
}
