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

	private Vision() {
		cs = CameraServer.getInstance();
		gp = new GripPipeline();
		cs.addAxisCamera("10.11.60.11");
		cs.startAutomaticCapture();
		CvSink camera = cs.getVideo(); //view/viewer_index.shtml?id=0
		Mat matrix = new Mat();
		camera.grabFrame(matrix);
		gp.process(matrix);
		contours = gp.filterContoursOutput();
		Point[] Points = contours.get(0).toArray();
		for(Point p: Points) {
			System.out.println(p.x + "  " + p.y);
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

