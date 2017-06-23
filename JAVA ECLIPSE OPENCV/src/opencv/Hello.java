package opencv;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_stitching.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;



public class Hello{
	
static String name = "a";
public static void main(String[] args) {
	Instant zero;
	System.out.println(zero = Instant.now());
    MatVector imgs = new MatVector();
    Mat mat = null , matout = new Mat();
    for (int i = 1;i==1||!mat.empty(); i++) {
    	mat = imread(name+" ("+i+").jpg");
    	imgs.resize(imgs.size()+1);
    	imgs.put(imgs.size()-1,mat);
	}
	imgs.resize(imgs.size()-1);
    Stitcher s = Stitcher.createDefault(true);
    System.out.println(imgs.size()+"  "+ChronoUnit.MILLIS.between(zero,Instant.now()));
    int status = s.stitch(imgs, matout);
    System.out.println(status+"  "+ChronoUnit.MILLIS.between(zero,Instant.now()));
    imwrite(name+".jpg", matout);
	}
}