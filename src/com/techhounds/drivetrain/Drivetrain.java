package com.techhounds.drivetrain;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.auton.MotionProfileUploader;
import com.techhounds.auton.TrajectoryPointPair;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
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
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talon.setSensorPhase(true);
		
		talon.config_kP(0, 0, 0); // FIXME: find values
		talon.config_kI(0, 0, 0); // TODO: do we want different PID for velocity/motion profiling modes?
		talon.config_kD(0, 0, 0);
		
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(60, 0);
		talon.configPeakCurrentLimit(100, 0);
		talon.configPeakCurrentDuration(500, 0);
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
        setDefaultCommand(new ArcadeDrive());
    }
}

