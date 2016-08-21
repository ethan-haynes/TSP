package travelingSalesPerson;

import java.awt.geom.Point2D;

class Population {
	private Tour[] tours;
	private int size;
	
	Population(int size, int numCity, Point2D[] locations) {
		this.size = size;
		this.tours = generateTours(numCity, locations);
	}
	
	Population(int totalPop) {
		this.size = totalPop;
		this.tours = new Tour[totalPop];
	}
	
	void mutate(int elitism, double mu) {
        for (int i = elitism; i < size; i++) {
            this.getTour(i).mutate(mu);
        }
	}

	private Tour[] generateTours(int numCity, Point2D[] locations) {
		Tour[] tours = new Tour[size];
		for (int i = 0; i < tours.length; i++) {
			tours[i] = new Tour();
			tours[i] = generateTour(numCity, locations);
		}
		return tours;
	}
	
	private Tour generateTour(int numCity, Point2D[] locations) {
		int[] cityIndex = FitnessUtil.nrnArrayGen(numCity);
		double distance = FitnessUtil.calcDistance(cityIndex, locations);
		return new Tour(cityIndex, distance);
	}
	
	int getSize() {
		return size;
	}

	Tour getFittest() {
		Tour tour = tours[0];
		for (int i = 1; i < tours.length; i++) {
			if (tours[i].getDistance() < tour.getDistance()) 
				tour = tours[i];
		}
		return tour;
	}

	public void setTour(int i, Tour tour) {
		this.tours[i] = tour;
	}
	
	public Tour getTour(int i) {
		return this.tours[i];
	}

	public void updateDistance(Point2D[] cities) {
		for (Tour tour : tours) {
			tour.setDistance(cities);
		}
	}
}
