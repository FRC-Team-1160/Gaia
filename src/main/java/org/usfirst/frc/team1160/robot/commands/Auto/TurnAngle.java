/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot.commands.Auto;
import org.usfirst.frc.team1160.robot.Robot;
import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngle extends Command implements RobotMap{

	public double targetAngle;
    
	public TurnAngle(double targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
      requires(Robot.dt);
      requires(Robot.vs);
      this.targetAngle = 90;
    	//this.targetAngle = Robot.vs.angleindegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.dt.setLowGear();
    	Robot.dt.resetPosition();
    	Robot.dt.resetGyro();
    	Robot.dt.resetTurnAngleIntegral();
    	Robot.dt.resetTime();
    	Robot.dt.startTime();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //this.targetAngle = Robot.vs.angleindegrees;
		Robot.dt.turnAngle(targetAngle);
		//time for the ghetto isFinished()
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      System.out.println("Gyro Yaw is: " + Robot.dt.getGyro().getYaw() + " and the target angle is: " + targetAngle);
    	if ((Math.abs(Robot.dt.getGyro().getYaw() - targetAngle) < GYRO_TOLERANCE)) {
			//Robot.dt.turnAngleCheck(targetAngle);
			return true;
		}
    	return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.setPercentOutput(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.dt.setPercentOutput(0);
    }
}