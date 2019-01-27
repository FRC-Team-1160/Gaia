/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team1160.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem implements RobotMap{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Lift instance;

  private WPI_TalonSRX leftTalon;
  private WPI_TalonSRX rightTalon;

  public static Lift getInstance(){
    if(instance == null){
      instance = new Lift();
    }
    return instance;
  }

  private Lift(){
    leftTalon = new WPI_TalonSRX(LIFT_LEFT);
    rightTalon = new WPI_TalonSRX(LIFT_RIGHT);
  }

  public void setUp(){  
    
    leftTalon.set(0.25);
    rightTalon.set(0.25);
  }

  public void setDown(){
    leftTalon.set(-0.25);
    rightTalon.set(-0.25);
  }

  public void Stop(){
    leftTalon.set(0);
    rightTalon.set(0);
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
