package com.techhounds.oi;

import com.techhounds.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class CubeDetectedTrigger extends Trigger {

    public boolean get() {
        return Robot.intake.isCubeDetected();
    }
}
