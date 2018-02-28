package com.techhounds.drivetrain;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.auton.MotionProfileUploader;
import com.techhounds.auton.TrajectoryPointPair;

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
		
	public Drivetrain() {
		motorRightMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_PRIMARY);
		motorRightFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_RIGHT_SECONDARY);
		motorLeftMain = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_PRIMARY);
		motorLeftFollower = RobotUtilities.getTalonSRX(RobotMap.DRIVE_LEFT_SECONDARY);
		
		rightUploader = new MotionProfileUploader(motorRightMain);
		leftUploader  = new MotionProfileUploader(motorLeftMain);
		
		new Notifier(rightUploader).startPeriodic(0.005);
		new Notifier(leftUploader).startPeriodic(0.005);
		
		configure(motorRightMain);
		configure(motorLeftMain);
		
		motorRightFollower.follow(motorRightMain);
		motorLeftFollower.follow(motorLeftMain);
		
		motorRightMain.setInverted(true);
		motorRightFollower.setInverted(true);
	}
	
	/**
	 * Configures the Talons to a default state
	 */
	private void configure(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotUtilities.CONFIG_TIMEOUT);
		talon.setSensorPhase(true);
		
		talon.config_kP(1, 0, RobotUtilities.CONFIG_TIMEOUT); // FIXME: find values
		talon.config_kI(0, 0, RobotUtilities.CONFIG_TIMEOUT); // TODO: do we want different PID for velocity/motion profiling modes?
		talon.config_kD(0, 0, RobotUtilities.CONFIG_TIMEOUT);
		
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
    
    public void pushPoint(TrajectoryPointPair point) {
    	motorRightMain.pushMotionProfileTrajectory(point.right);
    	motorLeftMain.pushMotionProfileTrajectory(point.left);
    }
    
    public double getLeftDistance() {
    	return motorLeftMain.getSelectedSensorPosition(0);
    }
    
    public double getLeftVelocity() {
    	return motorLeftMain.getSelectedSensorVelocity(0);
    }
    
    public double getRightDistance() {
    	return motorRightMain.getSelectedSensorPosition(0);
    }
    
    public double getRightVelocity() {
    	return motorRightMain.getSelectedSensorVelocity(0);
    }
    
    public void resetProfile() {
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

    public void initDefaultCommand() {
    	// ArcadeDrive set in OI
    }

	@Override
	public void initSD() {
	}

	@Override
	public void updateSD() {
	}

	@Override
	public void initDebugSD() {
		SmartDashboard.putData(this);
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("Drive Right Distance", getRightDistance());
		SmartDashboard.putNumber("Drive Right Velocity", getRightVelocity());
//		SmartDashboard.putNumber("Drive Right Profile Top Buffer", motorRightMain.getMotionProfileTopLevelBufferCount());
		// FIXME: getStatus() is causing hangs!!
//		SmartDashboard.putNumber("Drive Right Profile Bottom Buffer", getRightProfileStatus().btmBufferCnt);
//		SmartDashboard.putNumber("Drive Right Power", motorRightMain.getMotorOutputPercent());
//		SmartDashboard.putNumber("Drive Right Current", motorRightMain.getOutputCurrent());
		
		SmartDashboard.putNumber("Drive Left Distance", getLeftDistance());
		SmartDashboard.putNumber("Drive Left Velocity", getLeftVelocity());
//		SmartDashboard.putNumber("Drive Left Profile Top Buffer", motorLeftMain.getMotionProfileTopLevelBufferCount());
//		SmartDashboard.putNumber("Drive Left Profile Bottom Buffer", getLeftProfileStatus().btmBufferCnt);
//		SmartDashboard.putNumber("Drive Left Power", motorLeftMain.getMotorOutputPercent());
//		SmartDashboard.putNumber("Drive Left Current", motorLeftMain.getOutputCurrent());
	}
}

