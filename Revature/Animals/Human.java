package com.Revature.Animals;

public class Human extends Dog{ //extends dog to keep variables
// example of Abstraction because the Human class is implementing the the data from the Dogs class
	
	public Human(double height, boolean hasFur) {
		super.setHeight(height);
		super.setHasFur(hasFur);
	}

	@Override // example of Polymorphism because the height and hasFur variables are being overridden from the values
	public void setHeight(double height) { // that were passed in the previous height and hasFur variables from the 
		super.setHeight(height); // Human method
	}
	
	@Override 
	public void setHasFur(boolean hasFur) {
		super.setHasFur(hasFur);
	}
	
	@Override
	public String toString() { // automatic conversion to string that replaces the Dog string output in the previous class
		return "Human's height = " + height + " Feet, Does it have Fur? " + hasFur + "";
	}
	
	public String calculateHeight() {
		if (height > 3.0) // Determine if the animal is tall or short with 3.0 being considered the threshold 
			return "The (Human) Animal is Tall";
		else
			return "The (Human) Animal is Short";
	}
}
