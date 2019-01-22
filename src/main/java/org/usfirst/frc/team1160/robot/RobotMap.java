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
	public static int DT_LEFT_1 = 1;						//Ports
	public static int DT_LEFT_2 = 2;
	public static int DT_LEFT_3 = 3;
	public static int DT_RIGHT_1 = 4;
	public static int DT_RIGHT_2 = 5;
	public static int DT_RIGHT_3 = 6;
	public static int PCM = 10;
	public static final int DT_SOLENOID_0 = 0;
	public static final int DT_SOLENOID_1 = 7;
	public static final double GYRO_KP_2 = 0.019; //TurnAngle
	public static final double GYRO_KI = 0.00027;
	public static final double GYRO_KD = 0.0003; //0.01 * (-1.0/80.0);//TURN ANGLE KP
	public static final double GYRO_TOLERANCE = 0.5;				//Smaller value means higher accuracy but more time spent
	public static final double TURN_TIMEOUT = 2.5;
																//achieving said accuracy
	public static final double GYRO_CAP = 0.5; //max speed of the turn during TurnAngles

}
