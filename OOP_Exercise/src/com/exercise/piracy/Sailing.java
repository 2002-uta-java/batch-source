package com.exercise.piracy;

public interface Sailing {
	
	// All sailor have a standard greeting of "Ahoy there!"" by default
	public default void ahoy(){
		System.out.println("Ahoy there!"); 
	}
	
	// All sailors should have a rank and a corresponding duty, with the proper getter and setter for both
	public String getDuty() ;

	public void setDuty(String duty);

	public String getRank();
	
	public void setRank(String rank);
}
