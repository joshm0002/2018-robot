package com.techhounds.powerpack.basic;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorMiddle extends Command {
	
	double power = 0;
	double time = 0;
	Timer timer = new Timer();

    public SetElevatorMiddle() {
    	requires(Robot.powerPack);
    }

    protected void initialize() {
    	timer.start();
    	if (Robot.powerPack.isBottomSwitchTripped()) {
    		power = 0.5;
    		time = 0.3;
    		
    	} else if (Robot.powerPack.isTopSwitchTripped()) {
    		power = 0.1;
    		time = 0.3;
    	}
    }

    protected void execute() {
    	Robot.powerPack.setElevatorPower(power);
    }

    protected boolean isFinished() {
        return timer.get() > time;
    }

    protected void end() {}

    protected void interrupted() {}
}
