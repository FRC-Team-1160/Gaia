package org.usfirst.frc.team1160.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc.team1160.robot.Robot;
import org.usfirst.frc.team1160.robot.RobotMap;
import org.usfirst.frc.team1160.robot.commands.drive.ManualDrive;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;

import com.kauailabs.navx.frc.AHRS;


//import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends Subsystem implements RobotMap {

	private static DriveTrain instance;
	WPI_VictorSPX frontLeft;
	WPI_VictorSPX frontRight;
	private WPI_TalonSRX middleLeft, middleRight;
	WPI_TalonSRX backLeft;
	private WPI_TalonSRX backRight;

	/*
	 * turnAngle variables
	 */
	private double deltaTime;
	private double angle_difference_now;
	private double angle_difference;
	private double derivative;
	private double proportion;
	private double integral; 

	//modify speed of turns w/o affecting PID
	private double rightSideBoost;
	private double overallBoost;

	private Timer timer,timerCheck;
		//timerCheck is supposed to run only upon the turnAngle method
		//hitting ninety degrees and activating the checking clause

	private AHRS gyro;
	
	private DoubleSolenoid driveSwitch;
	
	public static DriveTrain getInstance()
	{

		if (instance == null)
		{
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private DriveTrain(){
		frontLeft = new WPI_VictorSPX(DT_LEFT_1);
		middleLeft = new WPI_TalonSRX(DT_LEFT_2);
		backLeft = new WPI_TalonSRX(DT_LEFT_3);
		frontRight = new WPI_VictorSPX(DT_RIGHT_1);
		middleRight = new WPI_TalonSRX(DT_RIGHT_2);
		backRight = new WPI_TalonSRX(DT_RIGHT_3);
		driveSwitch = new DoubleSolenoid(PCM, DT_SOLENOID_0, DT_SOLENOID_1);
		gyro = new AHRS(SerialPort.Port.kMXP);
		setFollower();
		timer = new Timer();
		timerCheck = new Timer();
		rightSideBoost = 1.00;
		overallBoost = 1.01;
	}
	public void setFollower(){
		frontLeft.follow(backLeft);
		middleLeft.follow(backLeft);
		frontRight.follow(backRight);
		middleRight.follow(backRight);
	}
	public void manualDrive(){
		
		backLeft.set(ControlMode.PercentOutput, 0.8*(Math.pow(-(Robot.oi.getMainstick().getZ() - Robot.oi.getMainstick().getY()), 1)));
		backRight.set(ControlMode.PercentOutput, 0.8*(Math.pow(-(Robot.oi.getMainstick().getZ() + Robot.oi.getMainstick().getY()), 1)));
		SmartDashboard.putNumber("Yaw", gyro.getYaw());
		SmartDashboard.putNumber("Back Left Encoder", backLeft.getSelectedSensorPosition());
		SmartDashboard.putNumber("Back Right Encoder", backRight.getSelectedSensorPosition());
		SmartDashboard.putNumber("Back Left Error", backLeft.getClosedLoopError());
		SmartDashboard.putNumber("Back Right Error", backRight.getClosedLoopError());
		printEncoder();
		
//		SmartDashboard.putNumber("Angle", gyro.getAngle());
//		SmartDashboard.putNumber("Accel X", gyro.getWorldLinearAccelX());
//		SmartDashboard.putNumber("Accel Y", gyro.getWorldLinearAccelY());
//		SmartDashboard.putNumber("Accel Z", gyro.getWorldLinearAccelZ());
	}

	public WPI_TalonSRX getLeftMaster(){
		return backLeft;
	}

	public WPI_TalonSRX getRightMaster(){
		return backRight;
	}

	public  void printEncoder(){
		System.out.println("Left encoder" + backLeft.getSelectedSensorPosition());
		System.out.println("Right encoder" + backRight.getSelectedSensorPosition());
	}

	public void setPercentOutput(double percentOutput) {
		backLeft.set(ControlMode.PercentOutput, -1 * percentOutput);
		backRight.set(ControlMode.PercentOutput, percentOutput);
		}

	public void setOff() {
		driveSwitch.set(DoubleSolenoid.Value.kReverse);
	
		
	}
	
	public void setOn() {
		driveSwitch.set(DoubleSolenoid.Value.kForward);
		
	}

	public void resetAngleDifference() {
		angle_difference = 0;
	}
	public void resetTurnAngleIntegral() {
		integral = 0;
	}

	public void turnAngle(double targetAngle) { //ghetto PID with the navX sensor 
		double current_angle = gyro.getYaw();
		angle_difference_now = (targetAngle - current_angle);
		SmartDashboard.putNumber("Yaw", gyro.getYaw());
		proportion = GYRO_KP_2 * angle_difference;
 		deltaTime = getTime();
 		derivative = 0;//GYRO_KD * (angle_difference_now - angle_difference)/deltaTime;
 		if (Math.abs(angle_difference_now) < 15) {
		 integral += GYRO_KI*deltaTime*(angle_difference_now);
		 }
		if(integral > GYRO_KI_CAP) {
			integral = GYRO_KI_CAP;
		}

		integral = 0;
		System.out.println("The angle difference is:\t " + angle_difference + "\t and the angle differenece now is: " + angle_difference_now);

 		angle_difference = angle_difference_now;
 		
 		//SmartDashboard.putNumber("turnAngle PercentOutput input", proportion+derivative+integral);
 		//backLeft.set(ControlMode.PercentOutput, proportion+derivative+integral);
 		//backRight.set(ControlMode.PercentOutput, proportion+derivative+integral);
		//System.out.println("P is: \t" + proportion + "I is: \t" + integral + "D is: \t" + derivative);
		SmartDashboard.putNumber("P", proportion);
		SmartDashboard.putNumber("I", integral);
		SmartDashboard.putNumber("D", derivative);
		
		// if (proportion+derivative+integral <= GYRO_CAP) {
			// if(targetAngle > 0){
		backLeft.set(ControlMode.PercentOutput, -1*(proportion+derivative+integral));
	 	backRight.set(ControlMode.PercentOutput, -1*rightSideBoost*(proportion+derivative+integral));
			 //}//else{
			//	backLeft.set(ControlMode.PercentOutput, (proportion+derivative+integral));
			//	backRight.set(ControlMode.PercentOutput, rightSideBoost*(proportion+derivative+integral));
			 //}
	 		
 	//	}
 	//	else {
 	//		backLeft.set(GYRO_CAP);
 	//		backRight.set(GYRO_CAP);
	//	 }
 		
 		//printYaw();
 		resetTime();
 		startTime();
 	}
	
	public void turnAngleCheck(double targetAngle) {
		resetTimeCheck();
		startTimeCheck();
		while (getTimeCheck() < 1) {
			turnAngle(targetAngle);
		}
	}
 	
	/*
	 * Encoder Methods
	 */
	public void resetPosition() {
		backLeft.setSelectedSensorPosition(0,0,100);
		backRight.setSelectedSensorPosition(0,0,100);
				
	}

	/*
	 * Timer Methods
	 */
	public void resetTime(){
		timer.reset();
	}
	
	public void startTime(){
		timer.start();
	}
	
	public void stopTime(){
		timer.stop();
	}
	
	public double getTime(){
		return timer.get();
	}
	
	public boolean done(double finishTime) {
		return (timer.get() >= finishTime);
	}
	public void resetTimeCheck() {
		timerCheck.reset();
	}
	public void startTimeCheck() {
		timerCheck.start();
	}
	public void stopTimeCheck() {
		timerCheck.stop();
	}
	public double getTimeCheck() {
		return timerCheck.get();
	}

	public void resetGyro()
	{
		System.out.println(gyro.getYaw());
		gyro.reset();
		gyro.zeroYaw();
		System.out.println(gyro.getYaw());
	}
	public void zeroGyro() {
		gyro.zeroYaw();
	}
	
	public void printYaw() {
		//SmartDashboard.putNumber("Current Yaw", gyro.getYaw());
		//System.out.println("Current Yaw: " + gyro.getYaw());
	}
	public AHRS getGyro() {
		return gyro;
	}

	
	@Override
	protected void initDefaultCommand() {
    	setDefaultCommand(new ManualDrive());
	}
}