package com.exercise.piracy;

/*INHERITANCE:
 * 	Captain extends SailorAbstract class
 * */
public class Captain extends SailorAbstract{

	public Captain(){
		super();
		setRank("Captain");
		setDuty("everything around here");
	}
	
	public Captain(String name){
		super();
		setName(name);
		setRank("Captain");
		setDuty("everything around here");
	}
	
	// Implements the abstract function
	public void report() {
		System.out.println("I'm "+ getName()+" the "+getRank()+" I handle "+getDuty());
	}
	

}
