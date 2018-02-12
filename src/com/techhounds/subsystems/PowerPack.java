package com.techhounds.subsystems;
import com.techhounds.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerPack extends Subsystem {
	private Solenoid elevatorWinch;
	private Solenoid climberWinch;
	
	public PowerPack() {
		elevatorWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_ELEVATOR);
		climberWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_CLIMBER);
		configDefaults();
	}
	
	/**
	 * TODO: inversion
	 * TODO: current limit (peak & continuous)
	 * TODO: voltage limitation
	 */
	public void configDefaults() {
		
	}
	
	/**
	 * Switches between elevator & climber mode
	 * 
	 * FIXME: if you call off() then toggle(), both will 
	 * be enabled which is bad!
	 */
	public void toggle() {
		elevatorWinch.set(!elevatorWinch.get());
		climberWinch.set(!climberWinch.get());
	}
	
	/**
	 * Switches to elevator mode
	 */
	public void toElevator() {
		elevatorWinch.set(true);
		climberWinch.set(false);
	}
	
	/**
	 * Switches to climber mode
	 */
	public void toClimber() {
		elevatorWinch.set(false);
		climberWinch.set(true);
	}
	
	/**
	 * Disables both outputs
	 */
	public void off() {
		elevatorWinch.set(false);
		climberWinch.set(false);
	}
	
	/**
	 * @return whether the elevator is enabled
	 */
	public boolean getElevator() {
		return elevatorWinch.get();
	}
	
	/**
	 * @return whether the climber is enabled
	 */
	public boolean getClimber() {
		return climberWinch.get();
	}
	
    public void initDefaultCommand() {}
}

