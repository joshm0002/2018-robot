package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadElevatorControl extends Command {
	
	private final XboxController controller;
	private final int axis;

    public GamepadElevatorControl(XboxController controller, int axis) {
    	requires(Robot.powerPack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {}

    protected void execute() {
    	double forward = Math.pow(controller.getRawAxis(axis), 3);
   
    	Robot.powerPack.setElevatorPower(forward);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
