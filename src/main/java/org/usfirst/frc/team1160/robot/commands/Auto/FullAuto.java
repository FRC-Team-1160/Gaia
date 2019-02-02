/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot.commands.Auto;

import org.usfirst.frc.team1160.robot.commands.vision.*;
import org.usfirst.frc.team1160.robot.commands.Testbed.*;
import org.usfirst.frc.team1160.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FullAuto() {
    requires(Robot.vs);

    
    addSequential(new runVision());
    addSequential(new TurnAngle(Robot.vs.angleindegrees));
    addSequential(new DriveForwardVision(Robot.vs.distanceToTarget-2));
    addSequential(new ExtendPiston());
    //addSequential(new DriveForward(-10));
    //addSequential(new RetractPiston());

   
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
