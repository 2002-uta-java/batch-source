package com.revature.oopexcercise;

import com.revature.exceptions.NegativeWheelsException;

public abstract class Automobile implements Drivable{
	
	//Declaring the attribute of numOfWheels of a car as protected because wouldn't want to openly alter by making access modifier public
	protected int _numOfWheels;
	
	public int getNumOfWheels() {
		return _numOfWheels;
	}
	

	//Custom Exception Handler if input given by user is not valid
	public void setNumOfSides(int numOfWheels) {
		if(numOfWheels>4) {
			this._numOfWheels = numOfWheels;				
		} else {
			try {
				throw new NegativeWheelsException("Cannot have an automobile with less than 4 wheels: "+numOfWheels);
			} catch (NegativeWheelsException e) {
				e.printStackTrace();
			}
		}
	}
}
