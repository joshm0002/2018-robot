package com.techhounds.auton.paths;

import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleSide extends CommandGroup {

    public LeftScaleSide() {
    	addSequential(new DriveStraight(260, 0.7), 5);
    	addSequential(new DriveStraight(30, 0.4), 2);
    	addSequential(new TurnToAngleGyro(90), 2);
    	addSequential(new DriveStraight(-40, -0.4), 2);
    	addSequential(new SetElevatorPosition(ElevatorPosition.SCALE), 5);
    	addSequential(new SetIntakePower(-0.6), 1);
    }
}
