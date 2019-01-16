package org.usfirst.frc.team1160.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static GripPipeline gp;
	public static ArrayList<MatOfPoint> contours;
	public CameraServer cs;
	public static Vision instance;
	Point[] Points;
	public Mat matrix;
	public CvSink camera;
	private Vision() {
		cs = CameraServer.getInstance();
		gp = new GripPipeline();
		cs.addAxisCamera("axis","axis-camera.local");
		cs.addServer("axis");
		camera = cs.getVideo();
		matrix = new Mat();
		camera.grabFrame(matrix);
		if(!(matrix.empty())) {
			gp.process(matrix);
			contours = gp.filterContoursOutput();
			Points = contours.get(0).toArray();
		
		/*
		for(Point p: Points) {
			System.out.println(p.x + "  " + p.y);
		}
		*/
		}
		else {
			System.out.println("Did not work");
		}
	}
	
	public static Vision getInstance() {
		if (instance == null)
		{
			instance = new Vision();
		}
		return instance;
		
		
	}
	
	public void runVision() {
		
		camera.grabFrame(matrix);
		if(!(matrix.empty())) {
			gp.process(matrix);
			contours = gp.filterContoursOutput();
			Points = contours.get(0).toArray();
			System.out.println("Did work");
			double maxH = 0;
			double minH = 0;
			for(Point p : Points) {
				if(p.y > maxH) {
					maxH = p.y;
				}
				else if(p.y < minH) {
					minH = p.y;
				}
			}
			double height = maxH-minH;
			
			double distance = (6.5*414.923)/height;
			System.out.println("The pixel height is: " + height);
			System.out.println("The robot is: " + distance + "inches from the tape");
			
			//System.out.println(Points[0].y);
			
		}
		else {
			System.out.println("Did not work");
		}
		
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new org.usfirst.frc.team1160.robot.commands.vision.runVision());

    }
    
}

