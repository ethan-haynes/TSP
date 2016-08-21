import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import travelingSalesPerson.TourFacade;;

public class TSP {
	
	public static void main(String[] args) {
	    Scanner scan;
	    File file = new File("/Users/Haynes/Desktop/tsp/tsp1000.txt");
	    List<Double> coordinates = new ArrayList<>();
	    
	    try {
	        scan = new Scanner(file);
	        while(scan.hasNextDouble()) {
	        	coordinates.add(scan.nextDouble());
	        }
	    } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	    }
	    
	    TourFacade tf = new TourFacade(coordinates);
	    tf.start(); 
	}
}

