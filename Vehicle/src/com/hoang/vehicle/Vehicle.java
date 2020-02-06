package com.hoang.vehicle;

// abstraction
/* abstraction was implemented because I have abstract class which include the foundation
 * for functionality of a vehicle but not fully implemented a vehicle. 
*/ 
public abstract class Vehicle implements Performance {
	public int numOfWheels;
	
	public Vehicle() {
		super();
		
	}
	
	// getter
	public int getNumOfWheels() {
		return numOfWheels;
	}
	// setter
	public void setNumOfWheels(int numOfWheels) {
		
		if (numOfWheels > 0) {
			this.numOfWheels = numOfWheels;
		}
		else {
			try {
				throw new NegativeWheelsException("Cannot have a vehicle with wheels: "+numOfWheels);
			} 
			catch (NegativeWheelsException e) {
				e.printStackTrace();
			}
		}
	}
}
