package com.exercise.piracy;

/*INHERITANCE:
 * 	Pirate extends SailorAbstract class
 * */
public class Pirate extends SailorAbstract {
	
	public Pirate(){
		super();
		setRank("Swabby");
		setDuty("all the bloody cleaning");
		setMutinous(true);
		
	}
	
	public Pirate(String name){
		super();
		setName(name);
		setRank("Swabby");
		setDuty("all the bloody cleaning");
		setMutinous(true);
		
	}
	
	public void mutiny(SailorAbstract bob){
		String takenRank = bob.getRank();
		String takenDuty = bob.getDuty();
		String theirName = bob.getName();
		
		bob = new Pirate(theirName);
		
		this.promoted("Infamous Pirate "+takenRank, takenDuty);
		
	}
	
	
	// Implements the abstract function
	public void report() throws ScuvryDogException {
		System.out.println("I'm "+ getName()+" the "+getRank()+" I handle "+getDuty()+"...");
		System.out.println("	Oh no,  "+getName()+" is looking mutinous!");
		throw new ScuvryDogException();
	}
	
	@Override
	public void ahoy() {
		System.out.println("Yarr!");
	}
	
}
