package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.gyro.BNO055;

public class Gyroscope extends Subsystem {
	private static Gyroscope instance;
	private BNO055 gyro;
	private GyroBase gyroX;
	
	private Gyroscope() {
		gyro = BNO055.getInstance(I2C.Port.kOnboard);
		gyroX = gyro.createGyroX();
		gyroX.reset();
		
		// TODO: livewindow stuff
	}
	
	public static Gyroscope getInstance() {
		return instance == null ? instance = new Gyroscope() : instance;
	}
	
	/**
	 * Gets current rotation of the robot.
	 * @return rotation of the robot (degrees)
	 */
	public double getRotation() {
		return gyroX.getAngle();
	}
	
	public void reset() {
		gyroX.reset();
	}
	
	/**
	 * Updates the information on the SmartDashboard.
	 */
	public void updateSD() {
		SmartDashboard.putNumber("Rotation", getRotation());
	}
	
    public void initDefaultCommand() {}
}

