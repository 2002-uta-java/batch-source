package com.revature.abstracting;

public interface Behaving {

	// Function to tell what noise animal is making
	void MakesNoise();
	
	// Function to tell what animal is eating
	public String Eating(String food) throws CustomException;
	
	public String Eating(String food1, String food2);
	
}
