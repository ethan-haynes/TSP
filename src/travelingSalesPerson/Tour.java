package travelingSalesPerson;

import java.awt.geom.Point2D;

class Tour {
	private int[] cityIndex; 
	private double distance;
	
	Tour(int[] cityIndex, double distance) {
		this.cityIndex = cityIndex;
		this.distance = distance;
	}

	Tour(int size) {
		this.cityIndex = new int[size];
	}

	Tour() {
		super();
	}
	
	int length() {
		return cityIndex.length;
	}
	
	int[] getCitys() {
		return this.cityIndex;
	}
	
	private int getI(int i) {
		return this.cityIndex[i];
	}
	
	private void setI(int i, int value) {
		this.cityIndex[i] = value;
	}

	void setDistance(Point2D[] cities) {
		this.distance = FitnessUtil.calcDistance(cityIndex, cities);
	}
	
	double getDistance() {
		return this.distance;
	}
	
	private void copySequence(Tour t, int start, int end) {
		for (int i = start; i < end; i++) {
			this.setI(i, t.getI(i));
		}
	}
	
	Tour cross(Tour p) {
		//Pivot points
		int l,h;
		l = FitnessUtil.rndInt(1, this.length() - 2);
		h = FitnessUtil.rndInt(l, this.length());
		
		//empty Tour
		Tour child = new Tour(p.getCitys().length);
		
		child.crossHelper(this, p, l, h);

		child.replaceDups(this, l, h);

		return child;
	}
	
	private void crossHelper(Tour p1, Tour p2, int l, int h) {
		this.copySequence(p1, 0, l);
		this.copySequence(p2, l, h);
		this.copySequence(p1, h, this.length());
	}
	
	private void replaceDups(Tour p, int l, int h) {
		int[] c, uniq;
		c = this.getCitys(); //array of cities
		uniq = new int[h - l + 1]; //unique array
		
		int i = 0, j = 0, len;
		len = this.length();
		
		//Gather unique numbers
		for (int key = l; key < h; key++) {
			if (0 == FitnessUtil.count(c, p.getI(key), l, h)) {
				uniq[i++] = p.getI(key);
			}
		}

		for (int key = 0; key < len; key++) {
			if (key == l) key = h;
			
			if (0 != FitnessUtil.count(c, p.getI(key), l, h) && j <= i)
					this.setI(key, uniq[j++]);
		}
	}

	void mutate(double mu) {
		if (Math.random() < mu) {
			rndSwap();
		}
	}
	
	private void rndSwap() {
		rndSwap(1, this.length());
	}
	
	private void rndSwap(int i, int j) {
		int a, b;
		int tmp;

		a = FitnessUtil.rndInt(i, j);
		b = FitnessUtil.rndInt(i, j);
		tmp = this.getI(a);
		this.setI(a, this.getI(b));
		this.setI(a, tmp);
	}
}
