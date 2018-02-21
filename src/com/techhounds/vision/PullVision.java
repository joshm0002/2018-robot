package com.techhounds.vision;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PullVision extends Subsystem {
    public double getFrameCount() {
    	return SmartDashboard.getNumber("VisFrame", 0);
    }
    
    public boolean getFoundStatus() {
    	return SmartDashboard.getBoolean("FoundTarget", false);
    }
    
    public double getTurnAngle() {
    	return SmartDashboard.getNumber("AwayAngle", 0);
    }

    public void initDefaultCommand() {
    }
}

