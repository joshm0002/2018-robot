package com.techhounds.auton.paths;

import com.techhounds.arm.SetArm;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightSwitch extends CommandGroup {

    public StraightSwitch() {
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	addSequential(new DriveDistance(110, 3));
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new DelayedCommand(new SetArm(true), 0.5));
    }
}
