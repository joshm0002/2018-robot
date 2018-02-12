package com.techhounds.subsystems;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.commands.ArcadeDrive;
import com.techhounds.commands.auton.TrajectoryPointPair;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private TalonSRX motorRightMain;
	private TalonSRX motorRightFollower;
	private TalonSRX motorLeftMain;
	private TalonSRX motorLeftFollower;
	
	public final MotionProfileUploader rightUploader;
	public final MotionProfileUploader leftUploader;
	
	private MotionProfileStatus status;
		
	public Drivetrain() {
		motorRightMain = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_PRIMARY);
		motorRightFollower = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_SECONDARY);
		motorLeftMain = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_PRIMARY);
		motorLeftFollower = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_SECONDARY);
		
		rightUploader = new MotionProfileUploader(motorRightMain);
		leftUploader  = new MotionProfileUploader(motorLeftMain);
		
		new Notifier(rightUploader).startPeriodic(0.005);
		new Notifier(leftUploader).startPeriodic(0.005);
		
		configDefaults();
	}
	
	/**
	 * Configures the Talons to a default state
	 * 
	 * TODO: should we use timeouts on the config calls?
	 */
	public void configDefaults() {
//		motorRightFollower.set(ControlMode.Follower, 10);
//		motorLeftFollower.set(ControlMode.Follower, 25);
		
		motorRightFollower.follow(motorRightMain);
		motorLeftFollower.follow(motorLeftMain);
		
//		motorRightMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		//motorRightMain.setSensorPhase(true); // TODO: read from RobotMap
//		motorLeftMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		//motorLeftMain.setSensorPhase(true); // TODO: read from RobotMap
		
		motorRightMain.setInverted(true); // TODO
		motorRightFollower.setInverted(true);
		motorLeftMain.setInverted(false);
		motorLeftFollower.setInverted(false);

		// TODO: current limitation
		// TODO: voltage compensation/saturation
	}
	
	public void configModeVelocity() {
		configDefaults();
		
		motorRightMain.config_kP(0, 0, 0);
		motorRightMain.config_kI(0, 0, 0);
		motorRightMain.config_kD(0, 0, 0);
		motorLeftMain.config_kP(0, 0, 0);
		motorLeftMain.config_kI(0, 0, 0);
		motorLeftMain.config_kD(0, 0, 0);
	}
	
	public void configModePower() {
		configDefaults();
	}
	
	public void configModeMotionProfile() {
		configDefaults();

		motorRightMain.changeMotionControlFramePeriod(5); // TODO: store a constant for this
		motorLeftMain.changeMotionControlFramePeriod(5); // TODO: store a constant for this

		motorRightMain.config_kP(0, 0, 0);
		motorRightMain.config_kI(0, 0, 0);
		motorRightMain.config_kD(0, 0, 0);
		motorLeftMain.config_kP(0, 0, 0);
		motorLeftMain.config_kI(0, 0, 0);
		motorLeftMain.config_kD(0, 0, 0);

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
    	motorLeftMain.getMotionProfileStatus(status); // TODO: separate status for left/right
    	return status;
    }
    
    public MotionProfileStatus getRightProfileStatus() {
    	motorLeftMain.getMotionProfileStatus(status);
    	return status;
    }
    
    public void pushPoint(TrajectoryPointPair point) {
    	motorRightMain.pushMotionProfileTrajectory(point.right);
    	motorLeftMain.pushMotionProfileTrajectory(point.left);
    }
    
    public void resetProfile() {
//    	talon.clearMotionProfileTrajectories();
//    	if(getProfileStatus().hasUnderrun) {
//    		// TODO: log this
//    		talon.clearMotionProfileHasUnderrun(0);
//    	}
//    	talon.configMotionProfileTrajectoryPeriod(5, 0);
    	
    }


    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
}

