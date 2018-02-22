package com.techhounds.powerpack;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerPack extends Subsystem {
	
	private Solenoid transmission;
	private Solenoid brake;
	
	private WPI_TalonSRX winchPrimary;
	private WPI_TalonSRX winchSecondary;
	private WPI_TalonSRX winchTertiary;
	private WPI_TalonSRX winchQuaternary;
		
	public PowerPack() {
		transmission = new Solenoid(RobotMap.WINCH_TRANSMISSION);
		brake = new Solenoid(RobotMap.WINCH_BRAKE);
		
		winchPrimary = RobotUtilities.getTalonSRX(RobotMap.POWER_PACK_PRIMARY);
		winchSecondary = RobotUtilities.getTalonSRX(RobotMap.POWER_PACK_SECONDARY);
		winchTertiary = RobotUtilities.getTalonSRX(RobotMap.POWER_PACK_TERTIARY);
		winchQuaternary = RobotUtilities.getTalonSRX(RobotMap.POWER_PACK_QUATERNARY);
		
		winchSecondary.follow(winchPrimary);
		winchTertiary.follow(winchPrimary);
		winchQuaternary.follow(winchPrimary);
		
		configure(winchPrimary);
	}
	
	/**
	 */
	public void configure(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		talon.configPeakCurrentLimit(100, 0);
		talon.configPeakCurrentDuration(500, 0);
		talon.configContinuousCurrentLimit(60, 0);
		talon.enableCurrentLimit(true);
		
		talon.config_kP(0, 0, 0); // FIXME
		talon.config_kI(0, 0, 0);
		talon.config_kD(0, 0, 0);
		// TODO: allowed error
	}
	
	/**
	 * Switches between elevator & climber mode
	 * 
	 * FIXME: if you call off() then toggle(), both will 
	 * be enabled which is bad!
	 */
	public void toggleTransmission() {
		transmission.set(transmission.get());
	}
	
	/**
	 * Switches to elevator mode
	 */
	public void toElevator() {
		transmission.set(true);
	}
	
	/**
	 * Switches to climber mode
	 */
	public void toClimber() {
		transmission.set(false);
	}
	
	public void setTransmission(boolean elevator) {
		transmission.set(elevator);
	}
	
	public void setWinchPosition(double position){
		winchPrimary.set(ControlMode.Position, position);
	}
	
	public void setWinchVelocity(double velocity){
		winchPrimary.set(ControlMode.Velocity, velocity);
	}
	
	public void Power(double percent){
		winchPrimary.set(ControlMode.PercentOutput, RobotUtilities.constrain(percent));
	}
	
	public int getWinchPosition(){
		return winchPrimary.getSensorCollection().getAnalogIn();
	}
	
	public double getWinchVelocity(){
		return winchPrimary.getSensorCollection().getAnalogInVel();
	}
	
	public double getWinchMotorOutVolt(){
		return winchPrimary.getMotorOutputVoltage();
	}
	
	public double getWinchMotorCurrent(){
		return winchPrimary.getOutputCurrent();
	}
	
	/**
	 * @return whether the elevator is enabled
	 */
	public boolean getTransmission() {
		return transmission.get();
	}
	
	public void setBrake(boolean enabled) {
		brake.set(enabled);
	}
	
	public boolean getBrake() {
		return brake.get();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new SetPowerPackHold());
    }
}

