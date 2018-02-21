package com.techhounds.commands.vision;

import com.techhounds.Constants;
import com.techhounds.Robot;
import com.techhounds.RobotUtilities;
import com.techhounds.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngleGyro extends Command {
	private Drivetrain motors;
	private PIDController ctrl;
	private final double P = 0, I = 0, D = 0; // TODO: tune
	private double setAngle;

	/**
	 * Rotate robot to orientation of given angle.
	 * NOT relative to current robot orientation.
	 * @param angle in degrees
	 */
    public TurnToAngleGyro(double deg) {
    	motors = Robot.drivetrain;
    	requires(motors);
    	setAngle = deg;
    	ctrl = new PIDController(P, I, D, new PIDSource() {
    		public void setPIDSourceType(PIDSourceType pidSource) {}
    		
    		public PIDSourceType getPIDSourceType() {
    			return PIDSourceType.kDisplacement;
    		}
    		
    		public double pidGet() {
    			return Robot.gyro.getRotation();
    		}
    	}, new PIDOutput() {
    		public void pidWrite(double output) {
    			if (output > .05 && output < Constants.DRIVE_MIN_SPEED)
    				output = Constants.DRIVE_MIN_SPEED;
    			if (output < -0.5 && output > -Constants.DRIVE_MIN_SPEED)
    				output = -Constants.DRIVE_MIN_SPEED;
    			motors.setPower(RobotUtilities.constrain(-output), RobotUtilities.constrain(output));
    		}
    	});
    	ctrl.setAbsoluteTolerance(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("To Rotation PID", ctrl);
    	ctrl.setSetpoint(setAngle);
    	ctrl.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ctrl.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ctrl.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
