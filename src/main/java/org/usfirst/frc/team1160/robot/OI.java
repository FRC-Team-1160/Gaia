/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot;
import org.usfirst.frc.team1160.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1160.robot.commands.Testbed.ExtendPiston;
import org.usfirst.frc.team1160.robot.commands.Testbed.RetractPiston;
import org.usfirst.frc.team1160.robot.commands.drive.*;
import org.usfirst.frc.team1160.robot.commands.vision.runVision;
import org.usfirst.frc.team1160.robot.commands.Auto.TurnAngle;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap{
	private static OI instance;
	Joystick mainStick;
	JoystickButton setOn, setOff,extendPiston,retractPiston, runVision,turnToVisionAngle, resetYaw;
	
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI() {
		mainStick = new Joystick(0); //dual action
		createButtons();
	}

	private void createButtons() {
		// TODO Auto-generated method stub
		runVision = new JoystickButton(mainStick, 1);
		turnToVisionAngle = new JoystickButton(mainStick,2);
		setOn = new JoystickButton(mainStick, 3);
		setOff = new JoystickButton(mainStick, 4);
		extendPiston = new JoystickButton(mainStick,6);
		retractPiston = new JoystickButton(mainStick,5);
		resetYaw = new JoystickButton(mainStick, 7);
		System.out.println("Hello");
		tieButtons();
	}

	private void tieButtons() {
		// TODO Auto-generated method stub
		//climberUp.whileHeld(new Climb(1));
		//climberUp.whileHeld(new Climb(-1));
		runVision.whenPressed(new runVision());
		//System.out.println("The angle is: " + Robot.vs.angleindegrees);
		turnToVisionAngle.whenPressed(new TurnAngle(Robot.vs.angleindegrees));
		setOn.whenPressed(new setOn());
		setOff.whenPressed(new setOff());
		extendPiston.whenPressed(new ExtendPiston());
		retractPiston.whenPressed(new RetractPiston());
		resetYaw.whenPressed(new resetYaw());	}
	
	public Joystick getMainstick() {
		return mainStick;
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
