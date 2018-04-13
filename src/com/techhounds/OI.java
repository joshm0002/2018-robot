/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import com.techhounds.arm.GrabCube;
import com.techhounds.arm.SetArm;
import com.techhounds.auton.profiling.ProfileRecorder;
import com.techhounds.drivetrain.ArcadeDrive;
import com.techhounds.drivetrain.SetDriveDirection;
import com.techhounds.drivetrain.SetTransmission;
import com.techhounds.hook.SetHookPosition;
import com.techhounds.intake.DualGamepadIntakeControl;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.leds.FlashLEDs;
import com.techhounds.leds.SetLEDs;
import com.techhounds.oi.CubeDetectedTrigger;
import com.techhounds.oi.MatchTimeTrigger;
import com.techhounds.oi.RumbleDriver;
import com.techhounds.oi.RumbleOperator;
import com.techhounds.powerpack.GamepadClimberControl;
import com.techhounds.powerpack.GamepadElevatorControl;
import com.techhounds.powerpack.SetElevatorLimits;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPower;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final XboxController driver = new XboxController(0);
	public static final XboxController operator = new XboxController(1);
	public static boolean driveDirection = true;
	
	// TODO tune this, 5% to 10%
	public static final double CONTROLLER_DEADBAND = 0.02;

	public OI() {}

	/**
	 * Binds triggers/buttons to commands for the driver
	 */
	public static void setupDriver() {

		// Drivetrain default command is ArcadeDrive
		Robot.drivetrain.setDefaultCommand(new ArcadeDrive(driver, 1, 4));
		
		// Give both driver & operator intake control
		Robot.intake.setDefaultCommand(new DualGamepadIntakeControl(driver, operator, 3, 2));

		Button bA = new JoystickButton(driver, 1);
		// lower hook
		bA.whenPressed(new SetHookPosition(false));
		
		Button bB = new JoystickButton(driver, 2);
		// jiggle hook
		CommandGroup jiggleHook = new CommandGroup();
		jiggleHook.addSequential(new SetHookPosition(true));
		jiggleHook.addSequential(new WaitCommand(0.5));
		jiggleHook.addSequential(new SetHookPosition(false));
		bB.whenPressed(jiggleHook);

		Button bY = new JoystickButton(driver, 4);
		// ready for climb: raise hook, flip drive, and raise tilt
		bY.whenPressed(new SetHookPosition(true));
		bY.whenPressed(new SetDriveDirection(false));
		bY.whenPressed(new SetTiltPosition(TiltPosition.UP));

		Button RB = new JoystickButton(driver, 6);
		// high gear while held, low when released
		RB.whenPressed(new SetTransmission(true));
		RB.whenReleased(new SetTransmission(false));
		
		Button LB = new JoystickButton(driver, 5);
		// open arm while held, close when released
		LB.whenPressed(new SetArm(true));
		LB.whenReleased(new SetArm(false));
		
		Button start = new JoystickButton(driver, 8);
		// record motion profile
		start.toggleWhenPressed(new ProfileRecorder(0.02));
		
		Button select = new JoystickButton(driver, 7);
		// flip drive direction
		select.whenPressed(new SetDriveDirection(true));

		Trigger cubeDetect = new CubeDetectedTrigger();
		// rumble when we first get a cube
		cubeDetect.whenActive(new RumbleDriver(1));
		cubeDetect.whenActive(new SetLEDs(0, 255, 0));
		cubeDetect.whenInactive(new SetLEDs(0, 0, 0));
		
		Trigger sixtySeconds = new MatchTimeTrigger(75);
		// rumble when 60 seconds remain
		sixtySeconds.whenActive(new RumbleDriver(2));
		sixtySeconds.whenActive(new FlashLEDs(255, 255, 0, 0.5, 3));
	}

	/**
	 * Binds triggers/buttons to commands for the operator
	 */
	public static void setupOperator() {
		Trigger cubeRumble = new CubeDetectedTrigger();
		// rumble when we first lose a cube
		cubeRumble.whenActive(new RumbleOperator(1));
		cubeRumble.whenInactive(new RumbleOperator(1));

		Button bA = new JoystickButton(operator, 1);
		// Set elevator to DOWN position
		bA.whenPressed(new SetElevatorPosition(ElevatorPosition.COLLECT));

		Button bB = new JoystickButton(operator, 2);
		// Set elevator to SWITCH position
		bB.whenPressed(new SetElevatorPosition(ElevatorPosition.SWITCH));

		Button bX = new JoystickButton(operator, 3);
		// Actuate collector arms
		bX.whenPressed(new SetArm(true));
		bX.whenReleased(new SetArm(false));

		Button bY = new JoystickButton(operator, 4);
		// Set elevator to SCALE position
		bY.whenPressed(new SetElevatorPosition(ElevatorPosition.SCALE));

		Button LB = new JoystickButton(operator, 5);
		// OperatorClimberControl (joystick)
		LB.whileHeld(new GamepadClimberControl(operator, 1));

		Button RB = new JoystickButton(operator, 6);
		// OperatorElevatorControl (joystick)
		RB.whileHeld(new GamepadElevatorControl(operator, 5));

		Button arrowUp = getPOVButton(operator, 0);
		// Set Tilt to UP
//		arrowUp.whenPressed(new SetTiltPosition(TiltPosition.UP));
		arrowUp.whenPressed(new SetTiltPower(0.75));
		arrowUp.whenReleased(new SetTiltPower(-0.5));

//		Button arrowRight = getPOVButton(operator, 90);
		// Set Tilt to MIDDLE
//		arrowRight.whenPressed(new SetTiltPosition(TiltPosition.MIDDLE));

		Button arrowDown = getPOVButton(operator, 180);
		// Set Tilt to DOWN
//		arrowDown.whenPressed(new SetTiltPosition(TiltPosition.DOWN));
		arrowDown.whenPressed(new SetTiltPower(0.75));
		arrowDown.whenReleased(new SetTiltPower(-0.5));
		
		Button arrowLeft = getPOVButton(operator, 270);
		CommandGroup intakeGroup = new CommandGroup();
		intakeGroup.addParallel(new SetTiltPosition(Tilt.POS_DOWN));
		intakeGroup.addParallel(new GrabCube());
		intakeGroup.addParallel(new IntakeUntilDetected());
		arrowLeft.whileHeld(intakeGroup); //might get some weird results with it spam open/close arms
		
		Button start = new JoystickButton(operator, 8);
		// flash LED signal to human player
		start.whenPressed(new FlashLEDs(0, 0, 255, 0.25, 3));
		
		Button select = new JoystickButton(operator, 7);
		// Enable/disable elevator limits
		select.whileHeld(new SetElevatorLimits(false));
		select.whenReleased(new SetElevatorLimits(true));

	}

	/**
	 * Helper method to treat the POV inputs as buttons.
	 * 
	 * @param controller
	 * @param angle
	 * @return
	 */
	private static Button getPOVButton(GenericHID controller, int angle) {
		return new Button() {
			@Override
			public boolean get() {
				return controller.getPOV() == angle;
			}
		};
	}
	
	public static void rumbleDriver(boolean rumble) {
		if (rumble) {
			driver.setRumble(RumbleType.kLeftRumble, 1);
			driver.setRumble(RumbleType.kRightRumble, 1);
		} else {
			driver.setRumble(RumbleType.kLeftRumble, 0);
			driver.setRumble(RumbleType.kRightRumble, 0);
		}
	}	
	
	public static void rumbleOperator(boolean rumble) {
		if (rumble) {
			operator.setRumble(RumbleType.kLeftRumble, 1);
			operator.setRumble(RumbleType.kRightRumble, 1);
		} else {
			operator.setRumble(RumbleType.kLeftRumble, 0);
			operator.setRumble(RumbleType.kRightRumble, 0);
		}
	}
}
