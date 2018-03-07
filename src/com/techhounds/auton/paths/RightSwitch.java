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
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1));
    	addSequential(new DriveDistance(110), 3);
    	addSequential(new DriveDistance(40, 0, 0.4, 0)); // curve left
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new WaitCommand(1));
    	addSequential(new SetIntakePower(-0.5), 1);
    	addSequential(new DriveDistance(-20), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
    }
}
