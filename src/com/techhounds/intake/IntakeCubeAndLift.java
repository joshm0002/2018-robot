package com.techhounds.intake;

import com.techhounds.Robot;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCubeAndLift extends Command {
	
	Command tiltUp = new SetTiltPosition(TiltPosition.MIDDLE);

    public IntakeCubeAndLift() {
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
    	tiltUp.start();
    }

    protected void interrupted() {
    	// Do NOT call end() here!
    }
}
