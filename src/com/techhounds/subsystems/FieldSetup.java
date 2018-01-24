package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
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
 */
public class FieldSetup extends Subsystem {

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
		Unknown, Left, Middle, Right
	}

	/**
	 * Set to true to see diagnositic information on SmartDashboard.
	 */
	private static final boolean DEBUG = false;

	/**
	 * Allows user to specify starting position of robot.
	 */
	private final SendableChooser<Position> robotPositionChooser;

	/**
	 * Will be true once we know the field setup state.
	 */
	private boolean stateKnown;

	public FieldSetup() {
		super("FieldSetup");

		// We don't know the state of the field set up until
		// until update() is called and completes successfully
		stateKnown = false;

		// Setup SmartDashboard widget user interacts with to indicate where
		// the heck they placed the robot
		robotPositionChooser = new SendableChooser<>();
		robotPositionChooser.addDefault("Unknown", Position.Unknown);
		robotPositionChooser.addObject("Left", Position.Left);
		robotPositionChooser.addObject("Middle", Position.Middle);
		robotPositionChooser.addObject("Right", Position.Right);
		SmartDashboard.putData(robotPositionChooser);
	}

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
		// TODO - should return unknown if state not known
		return Position.Unknown;
	}

	/**
	 * Get position of our switch on field - based on FMS information.
	 * 
	 * @return Position of our side of the switch on field (from driver's
	 *         perspective) or {@link Position#Unknown} if not known yet.
	 */
	public Position getSwitchPosition() {
		// TODO - should return unknown if state not known
		return Position.Unknown;
	}

	/**
	 * Get position of our side of the scale - based on FMS information.
	 * 
	 * @return Position of our side of the scale on field (from driver's
	 *         perspective) or {@link Position#Unknown} if not known yet.
	 */
	public Position getScalePosition() {
		// TODO - should return unknown if state not known
		return Position.Unknown;
	}

	/**
	 * Determine if robot is positioned such that our side of the switch is straight
	 * ahead of us.
	 * 
	 * @return true if the state is known and the robots position is identical to
	 *         the switch position.
	 */
	public boolean isSwitchStraightAhead() {
		// TODO
		return false;
	}

	/**
	 * Determine if robot is positioned such that our side of the scale is straight
	 * ahead of us.
	 * 
	 * @return true if the state is known and the robots position is identical to
	 *         the switch position.
	 */
	public boolean isScaleStraightAhead() {
		// TODO
		return false;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO - Do we want a default command that just continuously calls update?
	}

	/**
	 * Updates the internal state based on current information from the
	 * SmartDashboard and DriverStation.
	 */
	public void update() {
		// Will be something like "LLL", "LRL", "RRR", etc where
		// first character is our switch ("L" if on left, "R" if on right)
		// second character is scale ("L" if on left, "R" if on right)
		// third character is their switch ("L" if on left, "R" if on right)
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		// TODO - Validate string and determine our switch and scale locations from string
		
		// Robot position is based on what driver has selected on dashboard
		Position robotStartPos = (Position) robotPositionChooser.getSelected();

		// TODO - set internal state variables based on values retrieved

		// TODO - set to true if successfully decoded all state information,
		// set to false if anything is still unknown
		stateKnown = false;

		// If debug output enabled, then show post information about the field setup
		if (DEBUG) {
			SmartDashboard.putBoolean("Field State", isStateKnown());
			SmartDashboard.putString("Switch Side", getSwitchPosition().name());
			SmartDashboard.putString("Scale Side", getScalePosition().name());
		}
	}

}
