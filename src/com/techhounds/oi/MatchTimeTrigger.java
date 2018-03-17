package com.techhounds.oi;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class MatchTimeTrigger extends Trigger {
	
	private final DriverStation ds = DriverStation.getInstance();
	private final double time;
	
	public MatchTimeTrigger(double timeSecs) {
		super();
		this.time = timeSecs;
	}

    public boolean get() {
        return ds.getMatchTime() == time;
    }
}
