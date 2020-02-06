package com.revature.shapes;

import com.revature.exceptions.NegativeSidesException;

public abstract class Shape implements Drawable, Calculatable {
	
	public int numOfSides;
	
	public Shape() {
		super();
	}
	
	public int getNumOfSides() {
		return numOfSides;
	}
	
	public void setNumOfSides(int numOfSides) {
		if(numOfSides>0) {
			this.numOfSides = numOfSides;				
		} else {
			try {
				throw new NegativeSidesException("Cannot have a shape with sides: "+numOfSides);
			} catch (NegativeSidesException e) {
				e.printStackTrace();
			}
		}
	}

	public int calculateArea() {
		// TODO Auto-generated method stub
		return 0;
	}
}
