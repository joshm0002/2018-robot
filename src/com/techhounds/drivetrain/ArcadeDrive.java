package com.techhounds.drivetrain;

import com.techhounds.OI;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TODO refactor to take controller & axis arguments
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {}

    /**
     * TODO: do we want to square/cube input?
     */
    protected void execute() {
    	double forward = -OI.getDriverAxis(1); //negatives are intentional
    	double turn = -OI.getDriverAxis(4);
    	
    	Robot.drivetrain.setPower(forward+turn, forward-turn);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
