package com.techhounds.arm;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleArm extends Command {

    public ToggleArm() {
    	requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.toggleArm();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
}
