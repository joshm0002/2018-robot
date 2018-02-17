package com.techhounds.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerPack extends Subsystem {
	
	private Solenoid elevatorWinch;
	private Solenoid climberWinch;
	
	private TalonSRX winch;
	private TalonSRX winch2;
	private TalonSRX winch3;
	private TalonSRX winch4;
	
	private double P = 0, I = 0, D = 0;
	private int PCL = 10000; //peak current limit
	private int CCL = 10000; //continuous current limit
	
	public PowerPack() {
		elevatorWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_ELEVATOR);
		climberWinch = new Solenoid(RobotMap.WINCH_TRANSMISSION_CLIMBER);
		winch = new WPI_TalonSRX(RobotMap.POWER_PACK_PRIMARY);
		winch2 = new WPI_TalonSRX(RobotMap.POWER_PACK_SECONDARY);
		winch3 = new WPI_TalonSRX(RobotMap.POWER_PACK_3);
		winch4 = new WPI_TalonSRX(RobotMap.POWER_PACK_4);
		winch2.follow(winch);
		winch3.follow(winch);
		winch4.follow(winch);
		configDefaults();
	}
	
	/**
	 * TODO: inversion
	 * TODO: current limit (peak & continuous)
	 * TODO: voltage limitation
	 */
	public void configDefaults() {
		winch.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		P = 0;
		I = 0;
		D = 0;
		updatePIDValues();
		winch.configPeakCurrentLimit(PCL, 100);
		winch.configContinuousCurrentLimit(CCL, 100);
		winch.enableCurrentLimit(true);
	}
	
	public void setPIDValues(double P, double I, double D){
		this.P = P;
		this.I = I;
		this.D = D;
		updatePIDValues();
	}
	
	public void updatePIDValues(){
		winch.config_kP(0, P, 0);
		winch.config_kI(0, I, 0);
		winch.config_kD(0, D, 0);
	}
	
	/**
	 * Switches between elevator & climber mode
	 * 
	 * FIXME: if you call off() then toggle(), both will 
	 * be enabled which is bad!
	 */
	public void toggle() {
		if(!elevatorWinch.get() && !climberWinch.get()){
			climberWinch.set(false);
			elevatorWinch.set(true);
		}
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
	
	public void setWinchPosition(double position){
		winch.set(ControlMode.Position, position);
	}
	
	public void setWinchVelocity(double velocity){
		winch.set(ControlMode.Velocity, velocity);
	}
	
	public void setWinchPercent(double percent){
		winch.set(ControlMode.PercentOutput, RobotUtilities.constrain(percent));
	}
	
	public void stopWinchMotor(){
		winch.set(ControlMode.PercentOutput, 0);
	}
	
	public int getWinchPosition(){
		return winch.getSensorCollection().getAnalogIn();
	}
	
	public double getWinchVelocity(){
		return winch.getSensorCollection().getAnalogInVel();
	}
	
	public double getWinchMotorOutVolt(){
		return winch.getMotorOutputVoltage();
	}
	
	public double getWinchMotorCurrent(){
		return winch.getOutputCurrent();
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

