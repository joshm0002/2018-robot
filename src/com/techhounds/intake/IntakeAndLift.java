package com.techhounds.intake;

import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeAndLift extends CommandGroup {

    public IntakeAndLift() {
        addSequential(new IntakeUntilDetected());
        addSequential(new SetTiltPosition(Tilt.POS_MID));
    }
}
