package org.usfirst.frc.team1160.robot.subsystems;

import org.usfirst.frc.team1160.robot.Robot;
import org.usfirst.frc.team1160.robot.RobotMap;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class PID extends Subsystem implements RobotMap{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private WPI_TalonSRX leftController,rightController;
	private static PID instance;
	private double proportion, integral, derivative, distance_difference, distance_difference_now, deltaTime, current_distance;


	private PID(WPI_TalonSRX lController, WPI_TalonSRX rController) {
		leftController = lController;
		rightController = rController;
	
		leftController.config_kP(0, .01, 0);
		leftController.config_kI(0, 0., 0);
		leftController.config_kD(0, 0., 0);
		rightController.config_kP(0, 0.01, 0);
		rightController.config_kI(0, 0., 0);
    rightController.config_kD(0, 0., 0);
    
    leftController.configAllowableClosedloopError(0, 100, 0);
    rightController.configAllowableClosedloopError(0, 100, 0);

    
	}
	
	public static PID getInstance(WPI_TalonSRX lController, WPI_TalonSRX rController) {
		if(instance == null) {
			instance = new PID(lController, rController);
		}
		return instance;
	}
	
	public void goDistance(double targetDistance) {

		double left_distance = Robot.dt.getLeftMaster().getSelectedSensorPosition();
		double right_distance = Robot.dt.getRightMaster().getSelectedSensorPosition();
		
		double left_difference_now = Math.abs(targetDistance - left_distance);
		double right_difference_now = Math.abs(targetDistance - right_distance);

		double left_proportion = GYRO_DKP * left_difference_now;
		double right_proportion = GYRO_DKP * right_difference_now;

 		deltaTime = Robot.dt.getTime();
		derivative = 0;
		/*
 		if (Math.abs(distance_difference_now) < 15) {
		    integral += GYRO_DKI*deltaTime*(distance_difference_now);
		}

		if(integral > GYRO_KI_CAP) {
			integral = GYRO_KI_CAP;
		}
		*/
		integral = 0;
		System.out.println("The angle difference is:\t " + distance_difference + "\t and the angle differenece now is: " + distance_difference_now);

 		distance_difference = distance_difference_now;
 		
		SmartDashboard.putNumber("I", integral);
		SmartDashboard.putNumber("D", derivative);

		leftController.set(ControlMode.Position,-1*((left_proportion+integral+derivative)*RobotMap.CONTROLLER_CONSTANT_L));
		rightController.set(ControlMode.Position,((right_proportion+integral+derivative)*RobotMap.CONTROLLER_CONSTANT_R));

 		Robot.dt.resetTime();
 		Robot.dt.startTime();
 	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}