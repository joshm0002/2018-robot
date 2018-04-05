package com.techhounds.auton.paths;

import com.techhounds.auton.drive.DriveStraight;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Baseline extends CommandGroup {

    public Baseline() {
    	addParallel(new SetTiltPosition(Tilt.POS_UP));
    	addSequential(new DriveStraight(100), 3);
    }
}
