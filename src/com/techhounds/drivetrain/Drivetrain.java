package com.techhounds.drivetrain;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements DashboardUpdatable {

	private WPI_TalonSRX motorRightMain;
	private WPI_TalonSRX motorRightFollower;
	private WPI_TalonSRX motorLeftMain;
	private WPI_TalonSRX motorLeftFollower;

	public static final double MAX_DRIVE_SPEED = 0.75;
	public static final double MIN_DRIVE_SPEED = 0.3; // TODO: set deadband?
	public static final double COUNTS_PER_INCH = 422;
//	public static final double COUNTS_PER_INCH = 437; //4096 / Math.PI * 6;
	public static final boolean DEBUG = false;

	public Drivetrain() {
		motorRightMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_PRIMARY, "Drivetrain", "Right Main");
		motorRightFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_SECONDARY, "Drivetrain", "Right Follower");
		motorLeftMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_PRIMARY, "Drivetrain", "Left Main");
		motorLeftFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_SECONDARY, "Drivetrain", "Left Follower");

		configure(motorRightMain);
		configure(motorLeftMain);
		configure(motorRightFollower);
		configure(motorLeftFollower);

		motorRightFollower.follow(motorRightMain);
		motorLeftFollower.follow(motorLeftMain);

		motorRightMain.setInverted(true);
		motorRightFollower.setInverted(true);
		motorRightMain.setSensorPhase(true);
		
		motorRightMain.configNominalOutputForward(0.15, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.configNominalOutputReverse(-0.15, RobotUtilities.CONFIG_TIMEOUT);
		
		motorLeftMain.configNominalOutputForward(0.15, RobotUtilities.CONFIG_TIMEOUT);
		motorLeftMain.configNominalOutputReverse(-0.15, RobotUtilities.CONFIG_TIMEOUT);
	}

	/**
	 * Configures the Talons to a default state
	 */
	private void configure(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotUtilities.CONFIG_TIMEOUT);
		talon.setSensorPhase(true); // TODO: why both here and in the constructor??

		talon.setNeutralMode(NeutralMode.Brake);
		
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(60, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakCurrentLimit(100, RobotUtilities.CONFIG_TIMEOUT);
		talon.configPeakCurrentDuration(500, RobotUtilities.CONFIG_TIMEOUT);
	}

	public void setPower(double right, double left) {
		motorRightMain.set(ControlMode.PercentOutput, RobotUtilities.constrain(right));
		motorLeftMain.set(ControlMode.PercentOutput, RobotUtilities.constrain(left));
	}

	public void setMotionProfile(SetValueMotionProfile mode) {
		motorRightMain.set(ControlMode.MotionProfile, mode.value);
		motorLeftMain.set(ControlMode.MotionProfile, mode.value);
	}

	public double getRawLeftDistance() {
		return motorLeftMain.getSelectedSensorPosition(0);
	}

	public double getRawLeftVelocity() {
		return motorLeftMain.getSelectedSensorVelocity(0);
	}

	public double getRawRightDistance() {
		return motorRightMain.getSelectedSensorPosition(0);
	}

	public double getRawRightVelocity() {
		return motorRightMain.getSelectedSensorVelocity(0);
	}

	public double getRawAverageDistance() {
		return (getRawRightDistance() + getRawLeftDistance()) / 2;
	}

	public double getScaledAverageDistance() {
		return getRawAverageDistance() / COUNTS_PER_INCH;
	}
	
	public double getScaledRightDistance() {
		return getRawRightDistance() / COUNTS_PER_INCH;
	}
	
	public double getScaledLeftDistance() {
		return getRawLeftDistance() / COUNTS_PER_INCH;
	}

	public void zeroEncoders() {
		motorRightMain.setSelectedSensorPosition(0, 0, 0);
		motorLeftMain.setSelectedSensorPosition(0, 0, 0);

	}

	public void initDefaultCommand() {
		// ArcadeDrive set in OI
	}

	@Override
	public void initSD() {
		if (DEBUG) {
			SmartDashboard.putData(this);
		}
	}

	@Override
	public void updateSD() {
		SmartDashboard.putNumber("Drive Right Counts", getRawRightDistance());
		SmartDashboard.putNumber("Drive Left Counts", getRawLeftDistance());
		SmartDashboard.putNumber("Drive Right Scaled Distance", getScaledRightDistance());
		SmartDashboard.putNumber("Drive Left Scaled Distance", getScaledLeftDistance());
		
		if (DEBUG) {
			SmartDashboard.putNumber("Drive Right Velocity", getRawRightVelocity());
			SmartDashboard.putNumber("Drive Left Velocity", getRawLeftVelocity());
			
			SmartDashboard.putNumber("Drive Left Power", motorLeftMain.getMotorOutputPercent());
			SmartDashboard.putNumber("Drive Left Current", motorLeftMain.getOutputCurrent());
		}
	}
}

