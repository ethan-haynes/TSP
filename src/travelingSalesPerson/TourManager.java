package travelingSalesPerson;

import java.awt.geom.Point2D;
import java.util.List;

class TourManager {
	private Point2D[] cities;
	private Point2D center;
	private int citySize;

	TourManager(List<Double> coordinates) {
    	this.citySize = (coordinates.size() - 2)/2;
    	this.center = getCenter(coordinates);
    	this.cities = gatherCities(coordinates);
	}
	
	private Point2D[] gatherCities(List<Double> coordinates) {
		double x = .0;
		double y = .0;
		Point2D[] pts = new Point2D[citySize];
		
		for (int i = 2; i < coordinates.size(); i++) {
			int index = i/2;
			if (i % 2 == 0) {
				if (2 < i) {
					pts[index-2] = new Point2D.Double(x,y);
				}
				x = coordinates.get(i);
			} else { 
				y = coordinates.get(i);
			}
		}
		
		pts[citySize-1] = new Point2D.Double(x,y);
		return pts;
	}
	
	private Point2D getCenter(List<Double> coordinates) {
		double x = coordinates.get(0);
		double y = coordinates.get(1);
		return new Point2D.Double(x/2,y/2);
	}

	Point2D[] getCities() {
		return this.cities;
	}
	
	int numCities() {
		return this.cities.length;
	}
}
