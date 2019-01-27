/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1160.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * Add your docs here.
 */
public class Arm extends Subsystem implements RobotMap{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Arm instance;
  private WPI_TalonSRX armTalonLeft;
  private WPI_TalonSRX armTalonRight;
  //private Solenoid armSolenoid;
   private boolean extended;

  public static Arm getInstance(){
    if(instance == null){
      instance = new Arm();
    }
    return instance;
  }

  private Arm(){
    armTalonLeft = new WPI_TalonSRX(ARM_LEFT);
    armTalonRight = new WPI_TalonSRX(ARM_RIGHT);
    //armSolenoid = new Solenoid(ARM_SOLENOID);
    extended = false;
  }

  //public void toggle(){
    //extended = !extended;
    //armSolenoid.set(extended);
  //}

  public void output(){
    armTalonLeft.set(1);
    armTalonRight.set(1);
  }

  public void stop(){
    armTalonLeft.set(0);
    armTalonRight.set(0);
  }

  public void intake(){
    armTalonLeft.set(-1);
    armTalonRight.set(-1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
