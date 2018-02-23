package com.techhounds.powerpack;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.Dashboard.DashboardUpdatable;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerPack extends Subsystem implements DashboardUpdatable {

	private Solenoid transmission;
	private Solenoid brake;

	private WPI_TalonSRX winchPrimary;
	private WPI_TalonSRX winchSecondary;
	private WPI_TalonSRX winchTertiary;
	private WPI_TalonSRX winchQuaternary;

	private final int PID_TOLERANCE = 3;

	public enum PowerPackState {
		CLIMBER,
		ELEVATOR,
		BRAKE
	}

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

		talon.configPeakOutputForward(100, 0);
		talon.configPeakOutputReverse(0, 0);

		talon.config_kP(0, 0, 0); // FIXME
		talon.config_kI(0, 0, 0);
		talon.config_kD(0, 0, 0);
		talon.config_kF(0, 0, 0);
		talon.configAllowableClosedloopError(0, PID_TOLERANCE, 0);
	}

	public void setWinchPosition(double position){
		winchPrimary.set(ControlMode.Position, position);
	}

	public void setWinchPower(double percent){
		winchPrimary.set(ControlMode.PercentOutput, RobotUtilities.constrain(percent));
	}

	public int getWinchPosition(){
		return winchPrimary.getSensorCollection().getAnalogIn();
	}

	public double getWinchVelocity(){
		return winchPrimary.getSensorCollection().getAnalogInVel();
	}
	
	public void setTransmission(boolean elevator) {
		transmission.set(elevator);
	}
	
	public void setBrake(boolean enabled) {
		brake.set(enabled);
	}

	public void setState(PowerPackState state) {
		switch(state) {
		case ELEVATOR:
			setTransmission(true);
			setBrake(false);
			break;
		case CLIMBER:
			setTransmission(false);
			setBrake(false);
			break;
		case BRAKE:
		default:
			setBrake(true);
			break;
		}
	}

	/**
	 * @return whether the elevator is enabled
	 */
	public boolean getTransmission() {
		return transmission.get();
	}

	public boolean getBrake() {
		return brake.get();
	}

	public boolean onTarget() {
		return Math.abs(winchPrimary.getClosedLoopError(0)) < PID_TOLERANCE;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new SetPowerPackHold());
	}

	@Override
	public void initSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDebugSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDebugSD() {
		// TODO Auto-generated method stub
		
	}
}

