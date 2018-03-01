package com.techhounds.auton.paths;

import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightScale extends CommandGroup {

    public StraightScale() {
    	addSequential(new DriveDistance(200, 3)); //FIXME real distance
    	addSequential(new SetElevatorPosition(ElevatorPosition.SCALE));
    	addSequential(new SetIntakePower(-1), 3);
    }
}
