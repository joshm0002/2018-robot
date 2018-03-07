package com.techhounds.auton.paths;

import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new DriveDistance(270, 270, 0.4, 0.4 * 0.95)); // less power left, curves left
    	addSequential(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new DelayedCommand(new SetIntakePower(-1), 0.5), 3);
    	addSequential(new WaitCommand(1));
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
