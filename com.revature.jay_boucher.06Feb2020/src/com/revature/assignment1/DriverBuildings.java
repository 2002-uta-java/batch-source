package com.revature.assignment1;

public class DriverBuildings {
          
	
	public static void main(String[] args) {
		Residential r = new Residential("Medium", 6);
		System.out.print("This is a Residential Home which is consider to be " + r.Buildings_Size());
		System.out.print(" the number of rooms " + r.Building_Rooms());
		System.out.print(" The cost of the home is " + r.HomePrice());
		
	}
}
	