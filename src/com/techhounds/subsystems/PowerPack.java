package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PowerPack extends Subsystem {
	private Solenoid elevatorWinch;
	private Solenoid climberWinch;
	
	public PowerPack() {
		elevatorWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_ELEVATOR);
		climberWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_CLIMBER);
	}
	
	public void toggle() { // switches elevatorWinch and climberWinch
		elevatorWinch.set(!elevatorWinch.get());
		climberWinch.set(!climberWinch.get());
	}
	public void toElevator() { //switches winch to the elevator
		elevatorWinch.set(true);
		climberWinch.set(false);
	}
	public void toClimber() { //switches winch to the climber
		elevatorWinch.set(false);
		climberWinch.set(true);
	}
	public void off() { //sets both to false
		elevatorWinch.set(false);
		climberWinch.set(true);
	}
	public boolean getElevator() { //returns the state of the elevator winch
		return elevatorWinch.get();
	}
	public boolean getClimber() { //returns the state of the climber winch
		return climberWinch.get();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

