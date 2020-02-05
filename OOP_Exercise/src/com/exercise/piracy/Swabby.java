package com.exercise.piracy;

/*INHERITANCE:
 * 	Swabby extends SailorAbstract class
 * */
public class Swabby extends SailorAbstract {
	
	public Swabby(){
		super();
		setRank("Swabby");
		setDuty("cleaning the decks");
	}
	
	public Swabby( String name) {
		setName(name);
		setRank("Swabby");
		setDuty("cleaning the decks");
	}
	

	public void report() {
		System.out.println("I'm "+ getName()+" the "+getRank()+" I handle "+getDuty());
	}
	
	
}
