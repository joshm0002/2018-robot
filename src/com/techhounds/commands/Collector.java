package com.techhounds.commands;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Collector extends Command {

	public Collector() {
    	requires(Robot.intake);
    	SmartDashboard.putNumber("Collector Power", 1);
    }

    protected void initialize() {
    	Robot.intake.configDefaults();
    }
    
    public void execute() {
    	double power = SmartDashboard.getNumber("Collector Power", 1);
    	Robot.intake.setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

}
