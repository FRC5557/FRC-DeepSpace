package org.usfirst.frc.team5557.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5557.robot.commands.ExpellCommand;
import org.usfirst.frc.team5557.robot.commands.ExtendLinearActuatorCommand;
import org.usfirst.frc.team5557.robot.commands.IntakeCommand;
import org.usfirst.frc.team5557.robot.commands.RetractLinearActuatorCommand;
//import org.usfirst.frc.team5557.robot.commands.SonicDriveCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * it uses the robotmap and commands to create buttons that run their respective commands
 */
public class OI {
	
	// Joysticks defined here
	public static final Joystick driveStickZero = new Joystick(RobotMap.JOYSTICK_DRIVE_ONE);

	// Buttons defined here
	public final Button intakeButton = new JoystickButton(driveStickZero, RobotMap.INTAKE_BUTTON);
	public final Button expellButton = new JoystickButton(driveStickZero, RobotMap.EXPELL_BUTTON);
	public final Button swapControllerButton = new JoystickButton(driveStickZero, RobotMap.SWAP_CONTROLLER_BUTTON);
//	public final Button extendLinearAcutatorButton = new JoystickButton(driveStickZero, RobotMap.EXTEND_LINEAR_ACTUATOR_BUTTON);
//	public final Button retractLinearAcutatorButton = new JoystickButton(driveStickZero, RobotMap.RETRACT_LINEAR_ACTUATOR_BUTTON);

	
	public OI() {
		
//		extendLinearAcutatorButton.whileHeld(new ExtendLinearActuatorCommand());
//		retractLinearAcutatorButton.whileHeld(new RetractLinearActuatorCommand());
		intakeButton.whileHeld(new IntakeCommand());
		expellButton.whileHeld(new ExpellCommand());
		
//		swapControllerButtontton.whenPressed(new SwapDriveComand("CONTROLLER"));
	}

	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
