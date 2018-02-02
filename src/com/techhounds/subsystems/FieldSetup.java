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
	 * Set to true to see diagnostic information on SmartDashboard.
	 */
	private static final boolean DEBUG = true;

	/**
	 * Allows user to specify starting position of robot.
	 */
	private final SendableChooser<Position> robotPositionChooser;

	/**
	 * Will be true once we know the field setup state.
	 */
	private boolean stateKnown;

	/**
	 * Position of our side of the switch (Unknown, Left or Right).
	 */
	private Position switchPos;

	/**
	 * Position of our side of the scale (Unknown, Left or Right).
	 */
	private Position scalePos;

	/**
	 * Position of the robot (where the drive team told us they placed it).
	 */
	private Position robotStartPos;
	
	
	// distance from center line
	private double DFCL;

	/**
	 * Constructs a new instance in an unknown state - until the {@link #update()}
	 * method is invoked by disabledPeriodic().
	 */
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
		SmartDashboard.putNumber("Distance From Center Line (- is from left wall, + is from right wall)", 0.0);
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
	public boolean isSwitchStraightAhead() {
		if (switchPos == Position.Unknown) {
			return false;
		} else {
			return robotStartPos == switchPos;
		}
	}
	
	public boolean isSwitchDiag(){
		if (switchPos == Position.Unknown){
			return false;
		}else{
			return (robotStartPos == Position.Middle && (getCenterPosition() < 60 && getCenterPosition() > -60));
		}
	}
	
	public boolean isScaleDiag(){
		if(switchPos == Position.Unknown){
			return false;
		}else{
			return (robotStartPos != Position.Middle && robotStartPos != scalePos && (getCenterPosition() > 60 || getCenterPosition() < -60));
		}
	}
	
	public boolean isSideSwitch(){
		if(switchPos == Position.Unknown){
			return false;
		}else{
			return ((robotStartPos == switchPos || robotStartPos == Position.Middle) && (getCenterPosition() > 60 || getCenterPosition() < -60));
		}
	}
	
	//above methods are possibly redundant

	/**
	 * Determine if robot is positioned such that our side of the scale is straight
	 * ahead of us.
	 * 
	 * @return true if the state is known and the robots position is identical to
	 *         the scale position.
	 */
	public boolean isScaleStraightAhead() {
		if (scalePos == Position.Unknown) {
			return false;
		} else {
			return robotStartPos == scalePos;// add distance check for this (determine if we need to turn or not)
		}
	}

	//DFCL = distance from center line
	public double getCenterPosition() {
		DFCL = SmartDashboard.getNumber("Distance From Center Line (- is from left wall, + is from right wall)", 0);
		return DFCL;
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Updates the internal state based on current information from the
	 * SmartDashboard and DriverStation.
	 */
	public void update() {
		
	    /** 
	     * Will be something like "LLL", "LRL", "RRR", etc where
		 * first character is our switch ("L" if on left, "R" if on right)
		 * second character is scale ("L" if on left, "R" if on right)
		 * third character is their switch ("L" if on left, "R" if on right) 
		 */
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
		// If debug output enabled, then show post information about the field setup
		SmartDashboard.putBoolean("Field State", isStateKnown());
		if (DEBUG) {
			SmartDashboard.putString("Switch Side", getSwitchPosition().name());
			SmartDashboard.putString("Scale Side", getScalePosition().name());
			SmartDashboard.putBoolean("Switch ahead", isSwitchStraightAhead());
			SmartDashboard.putBoolean("Scale ahead", isScaleStraightAhead());
			SmartDashboard.putNumber("debug: DFW", getCenterPosition());
		}
	}
}