package com.techhounds.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public abstract class AutonOption extends CommandGroup {
	
	public String name;
	
	public AutonOption (String name) {
		this.name = name;
		run();
	}

    public abstract void run();
}
