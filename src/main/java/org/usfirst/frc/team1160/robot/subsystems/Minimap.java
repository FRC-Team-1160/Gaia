/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1160.robot.subsystems;
import org.usfirst.frc.team1160.robot.Robot;
import java.net.*;
import java.util.Timer;
import java.io.*; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.AnalogAccelerometer;

/**
 * Add your docs here.
 */
public class Minimap extends Subsystem{
    
  public static Minimap instance;
  static double coordinates[] = new double[3];
  public Minimap(){
    coordinates[0] = 0.0;
    coordinates[1] = 0.0;
    coordinates[2] = 0.0;
  }

  public Minimap getInstance(){
    if(instance != null){
      instance = new Minimap();
    }
    return instance;
  }

/**********************************************************************
 * Finds a new position by integrating acceleration (multiply accel by time twice)
 * Then it updates it to the coordinate array
 * Still needs to confirm: ability to display 
 **********************************************************************/

 //initialize the coordinate array
 

 static double[] updatePosition() { 
    //begins the timer
    Robot.dt.startTime();
    double timeValue = Robot.dt.getTime();

    //creates an instance of the accelerometer to get the accel values
    BuiltInAccelerometer accel = new BuiltInAccelerometer();
    double xValue = accel.getX();
    double yValue = accel.getY();
    double zValue = accel.getZ();

    //multiply acceleration by time^2 to get position
    double changeXPosition = xValue * timeValue * timeValue;
    double changeYPosition = yValue * timeValue * timeValue;
    double changeZPosition = zValue * timeValue * timeValue;

    //save position into the coordinates
    coordinates[0] = coordinates[0] + changeXPosition;
    coordinates[1] = coordinates[1] + changeYPosition;
    coordinates[2] = coordinates[2] + changeZPosition;
    return coordinates;
  }
    public static void main(String args[]) throws IOException 
    { 
        //Client client = new Client("127.0.0.1", 12345); 
        Socket socket = null; 
        DataOutputStream out = null; 
        // establish a connection 
        try
        { 
            socket = new Socket("127.0.0.1", 12345); 
            System.out.println("Connected"); 
            
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
        int j = 0;
        float xDis = 50, yDis = 70, angle = 45;
        while (j < 9999999)
        {
	        long time = System.currentTimeMillis();
	        while (System.currentTimeMillis() - time < 1000) {}
	        out.writeBytes(xDis + " " + yDis + " " + angle + "\n");
	        xDis+=5;
	        j++;
        }
        
        System.out.println("ended");
        // close the connection 
        try
        { 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
