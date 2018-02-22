package com.techhounds.hook;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hook extends Subsystem {
	
	private WPI_TalonSRX hookMotor;
	
	public Hook() {
		hookMotor = RobotUtilities.getTalonSRX(RobotMap.HOOK_MOTOR);
	}
	
	public void setPower(double power) {
		hookMotor.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}

    public void initDefaultCommand() {
    }
}

