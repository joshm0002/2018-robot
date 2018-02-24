package com.techhounds.auton;

import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterToLeftSwitch extends CommandGroup {

    public CenterToLeftSwitch() {
        addSequential(new MotionProfileExecutor(MotionProfile.CenterToLeftSwitch));
        addSequential(new SetElevatorPosition(SetElevatorPosition.ElevatorPosition.SWITCH));
        addSequential(new SetIntakePower(-1));
        //addSequential(new I);
    }
    
    
        
}
