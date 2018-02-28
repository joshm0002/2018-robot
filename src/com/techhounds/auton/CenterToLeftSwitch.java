package com.techhounds.auton;

import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;


/**
 *
 */
public class CenterToLeftSwitch extends AutonOption {

	public CenterToLeftSwitch() {
		super("CenterToLeftSwitch");
	}

	@Override
	public void run() {
		 addSequential(new MotionProfileExecutor(MotionProfile.CenterToLeftSwitch));
	        addSequential(new SetElevatorPosition(SetElevatorPosition.ElevatorPosition.SWITCH));
	        addSequential(new SetIntakePower(-1));
	}
    
    
        
}
