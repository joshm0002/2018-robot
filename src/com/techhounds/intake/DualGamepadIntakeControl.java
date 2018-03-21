package com.techhounds.intake;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DualGamepadIntakeControl extends Command {

	private final XboxController driver, operator;
	private final int forwardAxis;
	private final int reverseAxis;

    public DualGamepadIntakeControl(XboxController driver, XboxController operator, int forward, int reverse) {
    	requires(Robot.intake);
    	this.driver = driver;
    	this.operator = operator;
    	this.forwardAxis = forward;
    	this.reverseAxis = reverse;
    }

    protected void initialize() {}

    protected void execute() {
    	
    	double outMult;
    	//prevent over-shooting switch/scale
    	if (Robot.powerPack.isBottomSwitchTripped()) {
    		outMult = 1;
    	} else {
    		outMult = 0.35;
    	}
    	
    	double forward =  Math.pow(driver.getRawAxis(forwardAxis), 2) + Math.pow(operator.getRawAxis(forwardAxis), 2);
    	double reverse = (Math.pow(driver.getRawAxis(reverseAxis), 2) + Math.pow(operator.getRawAxis(reverseAxis), 2)) * outMult;
    	double power = forward - reverse;
    	
    	// stall the motors to hold the cube
    	if (Math.abs(power) < 0.05 && Robot.intake.isCubeDetected()) {
    		power = 0.1;
    	}
    	
    	Robot.intake.setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
