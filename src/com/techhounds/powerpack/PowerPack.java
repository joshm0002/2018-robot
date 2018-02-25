package com.techhounds.powerpack;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.powerpack.basic.SetElevatorBottom;
import com.techhounds.powerpack.basic.SetElevatorMiddle;
import com.techhounds.powerpack.basic.SetElevatorTop;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Default (false) is elevator
 * Default (false) is brake
 */
public class PowerPack extends Subsystem implements DashboardUpdatable {

	private Solenoid transmission;
	private Solenoid brake;

	private WPI_TalonSRX winchPrimary;
	private WPI_TalonSRX winchSecondary;
	private WPI_TalonSRX winchTertiary;
	private WPI_TalonSRX winchQuaternary;

	private final int PID_TOLERANCE = 3;

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
	private void configure(WPI_TalonSRX talon) {
//		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotUtilities.CONFIG_TIMEOUT);

//		talon.configPeakCurrentLimit(100, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakCurrentDuration(500, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configContinuousCurrentLimit(60, RobotUtilities.CONFIG_TIMEOUT);
//		talon.enableCurrentLimit(true);

//		talon.configPeakOutputForward(100, RobotUtilities.CONFIG_TIMEOUT);
//		talon.configPeakOutputReverse(0, RobotUtilities.CONFIG_TIMEOUT);

		talon.config_kP(0, 0, RobotUtilities.CONFIG_TIMEOUT); // FIXME
		talon.config_kI(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		talon.config_kD(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		talon.config_kF(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		talon.configAllowableClosedloopError(0, PID_TOLERANCE, RobotUtilities.CONFIG_TIMEOUT);
		
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, RobotUtilities.CONFIG_TIMEOUT);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, RobotUtilities.CONFIG_TIMEOUT);
	}
	
	public void setElevatorPower(double power) {
		brake.set(true);
		transmission.set(false);
		winchPrimary.overrideLimitSwitchesEnable(true);
		winchPrimary.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}
	
	public void setElevatorPosition(double position) {
		brake.set(true);
		transmission.set(false);
		winchPrimary.overrideLimitSwitchesEnable(true);
		winchPrimary.set(ControlMode.Position, RobotUtilities.constrain(position));
	}
	
	public void setClimberPower(double power) {
		brake.set(true);
		transmission.set(true);
		winchPrimary.overrideLimitSwitchesEnable(false);
		winchPrimary.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}
	
	public void setClimberPosition(double position) {
		brake.set(true);
		transmission.set(true);
		winchPrimary.overrideLimitSwitchesEnable(false);
		winchPrimary.set(ControlMode.Position, RobotUtilities.constrain(position));
	}
	
	public void setBrake() {
		winchPrimary.set(ControlMode.Disabled, 0);
		brake.set(false);
	}
	
	public boolean isBottomSwitchTripped() {
		return winchPrimary.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean isTopSwitchTripped() {
		return winchPrimary.getSensorCollection().isRevLimitSwitchClosed();
	}

	public int getWinchPosition(){
		return winchPrimary.getSelectedSensorPosition(0);
	}

	public double getWinchVelocity(){
		return winchPrimary.getSelectedSensorVelocity(0);
	}

	/**
	 * @return whether the climber is enabled
	 */
	public boolean isClimber() {
		return transmission.get();
	}
	
	public boolean isElevator() {
		return !isClimber();
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
		SmartDashboard.putData(this);
		SmartDashboard.putData("Climber Up", new SetClimberPower(0.7));
		SmartDashboard.putData("Climber Down", new SetClimberPower(-0.3));
		SmartDashboard.putData("Elevator Scale", new SetElevatorPosition(ElevatorPosition.SCALE));
		SmartDashboard.putData("Elevator Switch", new SetElevatorPosition(ElevatorPosition.SWITCH));
		SmartDashboard.putData("Elevator Collect", new SetElevatorPosition(ElevatorPosition.COLLECT));
		SmartDashboard.putData("Elevator Scale Basic", new SetElevatorTop());
		SmartDashboard.putData("Elevator Switch Basic", new SetElevatorMiddle());
		SmartDashboard.putData("Elevator Collect Basic", new SetElevatorBottom());
		SmartDashboard.putData("Power Pack Hold", new SetPowerPackHold());
	}

	@Override
	public void updateDebugSD() {
//		SmartDashboard.putNumber("Power Pack Position", winchPrimary.getSelectedSensorPosition(0));
//		SmartDashboard.putNumber("Power Pack Velocity", winchPrimary.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Power Pack Power", winchPrimary.getMotorOutputPercent());
		SmartDashboard.putNumber("Power Pack Current", winchPrimary.getOutputCurrent());
		SmartDashboard.putBoolean("Elevator Bottom Limit", isBottomSwitchTripped());
		SmartDashboard.putBoolean("Elevator Top Limit", isTopSwitchTripped());
		SmartDashboard.putBoolean("Power Pack Brake Enabled", !getBrake());
		SmartDashboard.putBoolean("Power Pack Climber State", isClimber());
	}
}

