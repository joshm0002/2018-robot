package com.techhounds.drivetrain;

import java.util.ArrayList;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.auton.profiling.MotionProfileUploader;
import com.techhounds.auton.profiling.TrajectoryPointSequence;

import edu.wpi.first.wpilibj.Notifier;
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

	public final MotionProfileUploader rightUploader;
	public final MotionProfileUploader leftUploader;

	private MotionProfileStatus status;

	public static final double MAX_DRIVE_SPEED = 0.75;
	public static final double MIN_DRIVE_SPEED = 0.3; // TODO: set deadband?
	public static final double COUNTS_PER_INCH = 422;
//	public static final double COUNTS_PER_INCH = 437; //4096 / Math.PI * 6;
	public static final boolean DEBUG = false;

	public Drivetrain() {

		status = new MotionProfileStatus();

		motorRightMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_PRIMARY, "Drivetrain", "Right Main");
		motorRightFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_SECONDARY, "Drivetrain", "Right Follower");
		motorLeftMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_PRIMARY, "Drivetrain", "Left Main");
		motorLeftFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_SECONDARY, "Drivetrain", "Left Follower");

		rightUploader = new MotionProfileUploader(motorRightMain);
		leftUploader  = new MotionProfileUploader(motorLeftMain);

		new Notifier(rightUploader).startPeriodic(0.005);
		new Notifier(leftUploader).startPeriodic(0.005);

		configure(motorRightMain);
		configure(motorLeftMain);
		configure(motorRightFollower);
		configure(motorLeftFollower);

		motorRightFollower.follow(motorRightMain);
		motorLeftFollower.follow(motorLeftMain);

		motorRightMain.setInverted(true);
		motorRightFollower.setInverted(true);
		motorRightMain.setSensorPhase(true);
		
		motorRightMain.config_kP(0, 0.05, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.config_kI(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.config_kD(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.config_kF(0, 0.19864, RobotUtilities.CONFIG_TIMEOUT); //0.19864
		motorRightMain.configNominalOutputForward(0.15, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.configNominalOutputReverse(-0.15, RobotUtilities.CONFIG_TIMEOUT);
		motorRightMain.configVoltageCompSaturation(11.6, RobotUtilities.CONFIG_TIMEOUT);
		
		motorLeftMain.config_kP(0, 0.05, RobotUtilities.CONFIG_TIMEOUT);
		motorLeftMain.config_kI(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		motorLeftMain.config_kD(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		motorLeftMain.config_kF(0, 0.21882 * 1.065, RobotUtilities.CONFIG_TIMEOUT); //0.21882
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

	public MotionProfileStatus getLeftProfileStatus() {
		motorLeftMain.getMotionProfileStatus(status);
		return status;
	}

	public MotionProfileStatus getRightProfileStatus() {
		motorRightMain.getMotionProfileStatus(status);
		return status;
	}

	public void uploadMotionProfile(TrajectoryPointSequence points) {
		rightUploader.setPoints(points.rightPoints);
		leftUploader.setPoints(points.leftPoints);
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

	/**
	 * TODO: would it be easier to do this in the Uploader thread?
	 */
	public void resetProfile() {
		rightUploader.setPoints(new ArrayList<>());
		leftUploader.setPoints(new ArrayList<>());
		motorRightMain.clearMotionProfileTrajectories();
		motorLeftMain.clearMotionProfileTrajectories();
		if(getRightProfileStatus().hasUnderrun) {
			// TODO: log this
			motorRightMain.clearMotionProfileHasUnderrun(0);
		}    
		if (getLeftProfileStatus().hasUnderrun) {
			motorLeftMain.clearMotionProfileHasUnderrun(0);
		}
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
			
//			getRightProfileStatus();
//			SmartDashboard.putNumber("Drive Right Profile Top Buffer", motorRightMain.getMotionProfileTopLevelBufferCount());
//			SmartDashboard.putNumber("Drive Right Top Buf Rem", getRightProfileStatus().topBufferRem);
//			SmartDashboard.putNumber("Drive Right Profile Bottom Buffer", getRightProfileStatus().btmBufferCnt);
			SmartDashboard.putNumber("Drive Right Power", motorRightMain.getMotorOutputPercent());
			SmartDashboard.putNumber("Drive Right Current", motorRightMain.getOutputCurrent());
//			if (motorRightMain.getControlMode() == ControlMode.MotionProfile) {
//				SmartDashboard.putBoolean("Drive Right Active Point Valid", status.activePointValid);
//				SmartDashboard.putNumber("Drive Right Active Point Distance", motorRightMain.getActiveTrajectoryPosition());
//				SmartDashboard.putBoolean("Drive Right Active Point is Last", status.isLast);
//				SmartDashboard.putBoolean("Drive Right Active Point is Underrrun", status.isUnderrun);
//			}

//			SmartDashboard.putNumber("Drive Left Profile Top Buffer", motorLeftMain.getMotionProfileTopLevelBufferCount());
//			SmartDashboard.putNumber("Drive Left Profile Bottom Buffer", getLeftProfileStatus().btmBufferCnt);
			SmartDashboard.putNumber("Drive Left Power", motorLeftMain.getMotorOutputPercent());
			SmartDashboard.putNumber("Drive Left Current", motorLeftMain.getOutputCurrent());
//			SmartDashboard.putBoolean("Drive Left Active Point Valid", status.activePointValid);
//			SmartDashboard.putNumber("Drive Left Active Point Distance", motorRightMain.getActiveTrajectoryPosition());
//			SmartDashboard.putBoolean("Drive Left Active Point is Last", status.isLast);
//			SmartDashboard.putBoolean("Drive Left Active Point is Underrrun", status.isUnderrun);
		}
	}
}

