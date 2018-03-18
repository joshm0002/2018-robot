package com.techhounds.auton.paths;

import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnToAngleGyro;
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
public class CrossRightScale extends CommandGroup {

    public CrossRightScale() {
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	addSequential(new DriveStraight(218, 0.5), 8);
    	addSequential(new TurnToAngleGyro(85), 3);
    	addSequential(new DriveStraight(184, 0.5), 8);
    	
    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addParallel(new SetTiltPosition(Tilt.POS_MID));
    	addSequential(new TurnToAngleGyro(-10), 2);
    	
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveStraight(18, 0.4), 1);
    	addSequential(new SetIntakePower(-0.75), 1);
    }
}
