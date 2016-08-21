package travelingSalesPerson;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

class GeneticAlgorithm {
	private int popSize = 75;
	private double mu = .255;
	private int elitism = 1;
	private int nsSize = 4;
	private int genCount = 0;
	private int genMax = 100;
	
	
	GeneticAlgorithm(int popSize, double mu, int elitism, int nsSize) {
		this.popSize = popSize;
		this.mu = mu;
		this.elitism = elitism;
		this.nsSize = nsSize;
	}
	
	GeneticAlgorithm() {
		super();
	}
		
	Boolean canTerminate() {
		return  genMax <= genCount;
	}
	
	Population genPopulation(Point2D[] cities) {

		return new Population(popSize, cities.length, cities);
	}
	

	Population evolvePopulation(Population pop, Point2D[] cities) {
		
		Population newPop = evolveAndUpdate(pop, cities);
		genCount++;
		return newPop;
	}

	// Evolves a population over one generation
	private Population evolveAndUpdate(Population pop, Point2D[] cities) {
		
		Population nPop = new Population(popSize);

        nPop.setTour(0, pop.getFittest());

        for (int i = elitism; i < pop.getSize(); i++) {

            Tour p1 = ntrlSelection(pop, cities);
            Tour p2 = ntrlSelection(pop, cities);
            
            // Crossover parents
            Tour child = p1.cross(p2);

            nPop.setTour(i, child);
        }

        nPop.mutate(elitism, mu);
        nPop.updateDistance(cities);
        return nPop;
    }
    
    private Tour ntrlSelection(Population pop, Point2D[] cities) {
    	int l,h;
		l = FitnessUtil.rndInt(1, popSize - 2);
		h = FitnessUtil.rndInt(l, popSize);
    	
        Population ns = genPopulation(cities);

        for (int i = l; i < h; i++) {
            int r = FitnessUtil.rndInt(0, popSize);
            ns.setTour(i, pop.getTour(r));
        }

        Tour fittest = ns.getFittest();
        return fittest;
    }	
}
