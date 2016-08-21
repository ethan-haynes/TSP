package travelingSalesPerson;

import java.util.List;

public class TourFacade {
	private GeneticAlgorithm ga;
	private TourManager tm;
	
    public TourFacade(List<Double> coordinates) {
    	this.tm = new TourManager(coordinates);
    	this.ga = new GeneticAlgorithm();
	}

	public void start() {
		Population pop = ga.genPopulation(tm.getCities());

		while(!ga.canTerminate()) {

			pop = ga.evolvePopulation(pop, tm.getCities());
			
			Tour fittest = pop.getFittest();
			System.out.println(fittest.getDistance());
		}
		
		Tour fittest = pop.getFittest();

		System.out.println(fittest.getCitys().toString());
		System.out.println(fittest.getDistance());
    }
	
}
