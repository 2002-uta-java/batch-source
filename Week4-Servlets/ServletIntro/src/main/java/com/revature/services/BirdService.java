package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.models.Bird;

public class BirdService {
	
	private List<Bird> birds = new ArrayList<>();
	
	public BirdService() {
		birds.add(new Bird(1,"Tweety",50,"Canary"));
		birds.add(new Bird(2, "Fluffy", 20, "Crow"));
		birds.add(new Bird(3, "Sam", 40, "Toucan" ));
	}
	
	public List<Bird> getBirds(){
		return new ArrayList<Bird>(birds);
	}
	
	public List<Bird> getBirdsByBreed(String breed){
		return birds.stream().filter(b->breed.equals(b.getBreed())).collect(Collectors.toList());
	}
	
	public boolean addBird(Bird b) {
		return birds.add(b);
	}

}
