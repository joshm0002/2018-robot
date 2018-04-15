package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightCross extends CommandGroup {

    public RightCross() {
    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
    	
    	// drive across
    	addSequential(new DriveStraight(200, 0.7), 8);
    	addSequential(new DriveStraight(18, 0.4), 2);
    	addSequential(new TurnToAngleGyro(85), 3);
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_MID), 4));
    	addSequential(new DriveStraight(120, 0.6), 8);
    }
}
