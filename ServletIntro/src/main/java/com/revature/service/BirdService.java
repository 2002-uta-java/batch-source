package com.revature.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.models.Bird;

public class BirdService {
	private List<Bird> birds = new ArrayList<>();

	public BirdService() {
		super();

		birds.add(new Bird("Canary", 1, "Tweetie", 50));
		birds.add(new Bird("Crow", 2, "Fluffy", 20));
		birds.add(new Bird("Toucan", 3, "Sam", 40));
	}

	public List<Bird> getBirds() {
		return new ArrayList<Bird>(birds);
	}

	public List<Bird> getBirdsByBreed(final String breed) {
//		final List<Bird> breeds = new LinkedList<Bird>();
//		
//		for(final Bird bird:birds)
//			if(breed.equalsIgnoreCase(bird.getBreed()))
//				 breeds.add(bird);
//		
//		return breeds;

		return birds.stream().filter(b -> breed.equalsIgnoreCase(b.getBreed())).collect(Collectors.toList());
	}
}
