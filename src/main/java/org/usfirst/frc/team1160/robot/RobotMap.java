/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
	/**
	 *
	 */

	public static final double _0_023 = 0.023;
	//purposly naenaed the port assignments cuz too lazy to get enconder values from the right talon by what talon we pull values from

	public static int DT_LEFT_1 = 1; // Ports
	public static int DT_LEFT_2 = 8;
	public static int DT_LEFT_3 = 3;
	public static int DT_RIGHT_1 = 4;
	public static int DT_RIGHT_2 = 6;
	public static int DT_RIGHT_3 = 5;
	public static int PCM = 10;
	public static final int DT_SOLENOID_0 = 0;
	public static final int DT_SOLENOID_1 = 7;
	public static final double GYRO_KP_2 = 0.04; //TurnAngle
	public static final double GYRO_KI = 0.01;
	public static final double GYRO_KD = 0.000001; //0.01 * (-1.0/80.0);//TURN ANGLE KP
	public static final double GYRO_TOLERANCE = 3;				//Smaller value means higher accuracy but more time spent
	public static final double TURN_TIMEOUT = 2.5;
																//achieving said accuracy
	public static final double GYRO_CAP = 0.5; //max speed of the turn during TurnAngles
	public static final double GYRO_KI_CAP = 0.05;

	//PID Stuff

	public static final double WHEEL_DIAMETER = 6; //inches
	public static final double CONTROLLER_CONSTANT_L = 2225/WHEEL_DIAMETER*Math.PI; 
	public static final double CONTROLLER_CONSTANT_R = 2225/WHEEL_DIAMETER*Math.PI; 
	
}
