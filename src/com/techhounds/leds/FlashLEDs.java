package com.techhounds.leds;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FlashLEDs extends CommandGroup {
	
	public FlashLEDs(int red, int green, int blue, int repetitions) {
		this(red, green, blue, 1, repetitions);
	}

    public FlashLEDs(int red, int green, int blue, double duration, int repetitions) {
    	for (int i = 0; i < repetitions; i++) {
    		addSequential(new SetLEDs(red, green, blue, duration));
    		addSequential(new SetLEDs(0, 0, 0, duration));
    	}
    }
}
