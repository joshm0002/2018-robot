package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PullVision extends Subsystem {
    private static PullVision instance;
    
    public static PullVision getInstance() {
    	return instance == null ? instance = new PullVision() : instance;
    }
    
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

