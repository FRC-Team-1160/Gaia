package org.usfirst.frc.team1160.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1160.robot.RobotMap;
/**
 *
 */
public class Testbed extends Subsystem implements RobotMap{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Testbed instance;
	private DoubleSolenoid piston;
	
	private Testbed(){
		
		piston = new DoubleSolenoid(PISTON_SOLENOID_1, PISTON_SOLENOID_2);
		
	}
	
	public static Testbed getInstance(){
		if (instance == null){
			instance = new Testbed();
		}
		return instance;
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void extendPiston(){
    	piston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPiston(){
    	piston.set(DoubleSolenoid.Value.kReverse);
    }
}

