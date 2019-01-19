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
public class Belt extends Subsystem implements RobotMap {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Belt instance;
  private WPI_TalonSRX beltTalon;

  public static Belt getInstance(){
    if(instance == null){
      instance = new Belt();
    }
    return instance;
  }

  private Belt(){ 
    beltTalon = new WPI_TalonSRX(BELT);

  }

  public void setForward(){
    beltTalon.set(1);//Moves belt forward
  }

  public void setReverse(){
    beltTalon.set(-1);//Moves belt backwards
  }

  public void Stop(){
    beltTalon.set(0);
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
