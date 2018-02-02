package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchTransmission extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Solenoid elevator;
	private Solenoid climber;
	public void WinchTransmission() {
		elevator = new Solenoid(RobotMap.WINCH_TRANSMISSION_ELEVATOR);
		climber = new Solenoid(RobotMap.WINCH_TRANSMISSION_CLIMBER);
	}
	public void disable() {
		elevator.set(false);
		climber.set(false);
	}
	public void toElevator() {
		elevator.set(true);
		climber.set(false);
	}
	public void toClimber() {
		elevator.set(false);
		climber.set(true);
	}
	public void toggle() {
		if(elevator.get() != climber.get() ) {
			elevator.set(!elevator.get());
			climber.set(!climber.get());
		}
		else {
			toElevator();
		}
	}
	public boolean getElevator() {
		return elevator.get();
	}
	public boolean getClimber() {
		return climber.get();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

