package travelingSalesPerson;

import java.awt.geom.Point2D;
import java.util.Random;

public final class FitnessUtil {

	// count the number of occurrence of key in a from start to end
	static int count(int[] a, int k, int start, int end) {
		int count = 0;
		for (int i = start; i < end; i++) {
			if (a[i] == k)
				count++;
		}
		return count;
	}
	
	//non-repeating random number generator [0..n]
	static int[] nrnArrayGen(int size) {
	    int[] arr = new int[size];
	    for (int i = 0; i < arr.length; i++) {
	    	arr[i] = i;
	    }
	    return fisherYates(arr);
	}
	
	private static int[] fisherYates(int[] arr) {
		Random r = new Random();
		for (int i = arr.length - 1; 0 < i; i--) {
			int index = r.nextInt(i);
			int tmp = arr[index];
			
			arr[index] = arr[i];
			arr[i] = tmp;	
		}
		return arr;
	}

	static double calcDistance(int[] cIndex, Point2D[] loc) {
		double distance = .0;
		for (int i = 1; i < cIndex.length; i++) {
			distance += loc[cIndex[i-1]].distance(loc[cIndex[i]]);
		}
		return distance;
	} 
	
	static int rndInt(int m, int n) {
		if (n < m) throw new IllegalArgumentException();

		Random r = new Random();
		return r.nextInt(n-m) + m;
	}
}