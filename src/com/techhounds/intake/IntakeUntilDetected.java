package com.techhounds.intake;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilDetected extends Command {

    public IntakeUntilDetected() {
    	requires(Robot.intake);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.intake.setPower(1);
    }

    protected boolean isFinished() {
        return Robot.intake.isCubeDetected();
    }

    protected void end() {
    	Robot.intake.setPower(0);
    }

    protected void interrupted() {
    	end();
    }
}
