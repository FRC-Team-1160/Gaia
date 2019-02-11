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
  public Minimap(){
  }

  public Minimap getInstance(){
    if(instance != null){
      instance = new Minimap();
    }
    return instance;
  }

/**********************************************************************
 * Finds a new position by integrating acceleration (multiply accel by time twice)
 * Still needs: find old position, find new position by updating it 
 **********************************************************************/
 static double[] updatePosition() { //rename once more specifically known
    Robot.dt.startTime();
    double timeValue = Robot.dt.getTime();
    BuiltInAccelerometer accel = new BuiltInAccelerometer();
    double xValue = accel.getX();
    double yValue = accel.getY();
    double zValue = accel.getZ();

    double changeXPosition = xValue * timeValue * timeValue;
    double changeYPosition = yValue * timeValue * timeValue;
    double changeZPosition = zValue * timeValue * timeValue;

    double coordinates[] = new double[3]; 
    coordinates[0] = changeXPosition;
    coordinates[1] = changeYPosition;
    coordinates[2] = changeZPosition;
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
