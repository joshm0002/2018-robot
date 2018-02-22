package com.techhounds.intake;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO refactor to take controller & axis arguments
 */
public class GamepadIntakeControl extends Command {

	private final XboxController controller;
	private final int forwardAxis;
	private final int reverseAxis;

    public GamepadIntakeControl(XboxController controller, int forward, int reverse) {
    	requires(Robot.intake);
    	this.controller = controller;
    	this.forwardAxis = forward;
    	this.reverseAxis = reverse;
    }

    protected void initialize() {}

    protected void execute() {
    	double forward = controller.getRawAxis(forwardAxis); // FIXME
    	double reverse = controller.getRawAxis(reverseAxis);
    	
    	Robot.intake.setPower(forward - reverse);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
