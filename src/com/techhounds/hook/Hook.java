package com.techhounds.hook;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;
import com.techhounds.Dashboard.DashboardUpdatable;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hook extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX hookMotor;
	
	public Hook() {
		hookMotor = RobotUtilities.getTalonSRX(RobotMap.HOOK_MOTOR);
		configure(hookMotor);
	}
	
	private void configure(WPI_TalonSRX talon) {
		
	}
	
	public void setPower(double power) {
		hookMotor.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}

    public void initDefaultCommand() {
    	// GamepadHookControl set in OI
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

