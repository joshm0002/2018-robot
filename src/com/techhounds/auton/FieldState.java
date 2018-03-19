
package com.techhounds.auton;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.gyro.Gyroscope;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Provides information about the initial state of the field (where robot is
 * position and our switch/scale is).
 * 
 * <p>
 * This is a "read only" subsystem in that you can get information (similar to
 * the {@link Gyroscope} subsystem). As such, this subsystem can be used
 * simultaneously by commands - you don't need to require it.
 * </p>
 * 
 * <p>
 * IMPORTANT: All positions are relative from the perspective of the driver
 * looking out on the field. Measured values (if any are implemented) are from
 * the center line of the field where negative values correspond to left of
 * center and positive values correspond to right of center.
 * </p>
 * 
 * <p>
 * The "update" method provided by this command should be called continuously
 * while the robot is disabled to make certain that we get the latest
 * information from the FMS. It does not need to be called during autonomous or
 * teleop as the information should not change.
 * </p>
 * 
 * FIXME: Since this is never require()'d by commands, and doesn't do anything like
 * initDefaultCommand, it doesn't really need to extend Subsystem.
 */
public class FieldState implements DashboardUpdatable {
	
	public static final boolean DEBUG = false;

	/**
	 * Possible starting positions for robots and side of field for switch/scale.
	 * 
	 * <p>
	 * Values are defined for ALL possible robot positions that the drive team can
	 * position the robot at the start of the match. Switch/Scale positions will
	 * only ever be Unknown, Left or Right.
	 * </p>
	 */
	public enum Position {
		/**
		 * Indicates position has not yet been determined or set.
		 */
		Unknown,
		/**
		 * Indicates position is on left side of field from drivers perspective.
		 */
		Left,
		/**
		 * Indicates position is center of field (robot only).
		 */
		Middle,
		/**
		 * Indicates position on right side of field looking out from driver station.
		 */
		Right
	}

	/**
	 * Allows user to specify starting position of robot.
	 */
	private final SendableChooser<Position> robotPositionChooser = new SendableChooser<>();

	/**
	 * Will be true once we know the field setup state.
	 */
	private boolean stateKnown = false;

	/**
	 * Position of our side of the switch (Unknown, Left or Right).
	 */
	private Position switchPos = Position.Unknown;

	/**
	 * Position of our side of the scale (Unknown, Left or Right).
	 */
	private Position scalePos = Position.Unknown;

	/**
	 * Position of the robot (where the drive team told us they placed it).
	 */
	private Position robotStartPos = Position.Unknown;
	
	/**
	 * Indicates whether we know the field setup state yet.
	 * 
	 * @return true once the field setup information is known.
	 */
	public boolean isStateKnown() {
		return stateKnown;
	}

	/**
	 * Get starting position of robot on field - based on where driver says they
	 * placed the robot.
	 * 
	 * @return Starting position of robot on field (from driver's perspective) or
	 *         {@link Position#Unknown} if not known yet.
	 */
	public Position getRobotPosition() {
		return robotStartPos;
	}

	/**
	 * Get position of our switch on field - based on FMS information.
	 * 
	 * @return Position of our side of the switch on field (from driver's
	 *         perspective) or {@link Position#Unknown} if not known yet.
	 */
	public Position getSwitchPosition() {
		return switchPos;
	}

	/**
	 * Get position of our side of the scale - based on FMS information.
	 * 
	 * @return Position of our side of the scale on field (from driver's
	 *         perspective) or {@link Position#Unknown} if not known yet.
	 */
	public Position getScalePosition() {
		return scalePos;
	}

	/**
	 * Determine if robot is positioned such that our side of the switch is straight
	 * ahead of us.
	 * 
	 * @return true if the state is known and the robots position is identical to
	 *         the switch position.
	 */
	public boolean isSwitchAhead() {
		return robotStartPos == Position.Middle || robotStartPos == switchPos;
	}
	
	public boolean isSwitchAcross(){
		return !isSwitchAhead();
	}
	
	/**
	 * Determine if robot is positioned such that our side of the scale is straight
	 * ahead of us.
	 * 
	 * @return true if the state is known and the robots position is identical to
	 *         the scale position.
	 */
	public boolean isScaleAhead() {
		return robotStartPos == scalePos;
	}
	
	public boolean isScaleAcross(){
		return robotStartPos != Position.Middle && robotStartPos != scalePos;
	}
	
	/**
	 * Updates the internal state based on current information from the
	 * SmartDashboard and DriverStation.
	 */
	public void pollData() {
		// Will be something like "LLL", "LRL", "RRR", etc where
		// first character is our switch ("L" if on left, "R" if on right)
		// second character is scale ("L" if on left, "R" if on right)
		// third character is their switch ("L" if on left, "R" if on right)
		switchPos = Position.Unknown;
		scalePos = Position.Unknown;
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		// Robot position is based on what driver has selected on dashboard
		robotStartPos = (Position) robotPositionChooser.getSelected();
		if (gameData.length() >= 2) {
			if (gameData.charAt(0) == 'L') {
				switchPos = Position.Left;
			} else if (gameData.charAt(0) == 'R') {
				switchPos = Position.Right;
			}
			if (gameData.charAt(1) == 'L') {
				scalePos = Position.Left;
			} else if (gameData.charAt(1) == 'R') {
				scalePos = Position.Right;
			}
		}

		if (switchPos == Position.Unknown || scalePos == Position.Unknown || robotStartPos == Position.Unknown) {
			stateKnown = false;
		} else {
			stateKnown = true;
		}
	}

	@Override
	public void initSD() {
		// Setup SmartDashboard widget user interacts with to indicate where
		// the heck they placed the robot
		robotPositionChooser.addDefault("Unknown", Position.Unknown);
		robotPositionChooser.addObject("Left", Position.Left);
		robotPositionChooser.addObject("Middle", Position.Middle);
		robotPositionChooser.addObject("Right", Position.Right);
		SmartDashboard.putData(robotPositionChooser);
	}

	@Override
	public void updateSD() {
		SmartDashboard.putBoolean("Field State", isStateKnown());
		
		if (DEBUG) {
			SmartDashboard.putString("Switch Side", getSwitchPosition().name());
			SmartDashboard.putString("Scale Side", getScalePosition().name());
			SmartDashboard.putBoolean("Switch ahead", isSwitchAhead());
			SmartDashboard.putBoolean("Scale ahead", isScaleAhead());
		}
	}
}