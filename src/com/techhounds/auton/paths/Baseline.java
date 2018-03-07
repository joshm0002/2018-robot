package com.techhounds.auton.paths;

import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Baseline extends CommandGroup {

    public Baseline() {
    	addParallel(new SetTiltPosition(Tilt.POS_UP));
    	addSequential(new DriveDistance(100), 3);
    }
}
