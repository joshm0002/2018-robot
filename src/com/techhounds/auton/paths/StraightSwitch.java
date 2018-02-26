package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.basic.SetElevatorMiddle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightSwitch extends CommandGroup {

    public StraightSwitch() {
    	addParallel(new DelayedCommand(new SetElevatorMiddle(), 1));
    	addSequential(new DriveDistance(110, 3));
    	addSequential(new SetIntakePower(-1), 3);
    }
}
