package com.techhounds.powerpack;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadClimberControl extends Command {

	private final XboxController controller;
	private final int axis;

    public GamepadClimberControl(XboxController controller, int axis) {
    	requires(Robot.powerPack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {}

    protected void execute() {
    	double power = -controller.getRawAxis(axis);
    	
    	power = (power > 0) ? (power * PowerPack.PEAK_CLIMBER_FWD) : (power * PowerPack.PEAK_CLIMBER_REV);
    	
    	Robot.powerPack.setClimberPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
