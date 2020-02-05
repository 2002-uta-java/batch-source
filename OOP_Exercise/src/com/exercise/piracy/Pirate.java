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
	
	public String mutiny(SailorAbstract formerCaptain){
		String theirName = formerCaptain.getName();		
		this.promoted("Infamous Pirate "+formerCaptain.getRank(), formerCaptain.getDuty());
		return theirName;
		
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
