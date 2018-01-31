package com.techhounds.subsystems;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private TalonSRX motorRightMain;
	private TalonSRX motorRightFollower;
	private TalonSRX motorLeftMain;
	private TalonSRX motorLeftFollower;
	
	public Drivetrain() {
		motorRightMain = new WPI_TalonSRX(0); // TODO: fix port numbers
		motorRightFollower = new WPI_TalonSRX(1);
		motorLeftMain = new WPI_TalonSRX(2);
		motorLeftFollower = new WPI_TalonSRX(3);
	}
	
	/**
	 * Configures the Talons to a default state
	 * 
	 * TODO: should we use timeouts on the config calls?
	 */
	public void configDefaults() {
		motorRightFollower.set(ControlMode.Follower, 0);
		motorLeftFollower.set(ControlMode.Follower, 2);
		
		motorRightMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		//motorRightMain.setSensorPhase(true); // TODO: read from RobotMap
		motorLeftMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		//motorLeftMain.setSensorPhase(true); // TODO: read from RobotMap
	}
	
	public void configModeVelocity() {
		configDefaults();
	}
	
	public void configModePower() {
		configDefaults();
	}
	
	public void configModeMotionProfile() {
		configDefaults();
		motorRightMain.changeMotionControlFramePeriod(5); // TODO: store a constant for this
		motorLeftMain.changeMotionControlFramePeriod(5); // TODO: store a constant for this
	}
	
	public void setPower(double right, double left) {
		motorRightMain.set(ControlMode.PercentOutput, constrain(right));
		motorLeftMain.set(ControlMode.PercentOutput, constrain(left));
	}
	
	public void setMotionProfile(SetValueMotionProfile mode) {
		
	}
	
	/**
	 * Limits the given value to -1, 1
	 * 
	 * @param value
	 * @return
	 */
	private double constrain(double value) {
		return Math.min(Math.max(value, -1), 1);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
}

