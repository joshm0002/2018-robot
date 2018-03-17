package com.techhounds.auton.util;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimultaneousCommands extends CommandGroup {

    public SimultaneousCommands(Command ... commands) {
    	// XXX I have no clue if this will work as expected
    	
    	for (Command c : commands) {
    		addParallel(c);
    	}
    }
}
