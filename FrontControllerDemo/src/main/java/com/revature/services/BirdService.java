package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.daos.BirdDao;
import com.revature.daos.BirdDaoImpl;
import com.revature.models.Bird;

public class BirdService {

	private BirdDao birdDaoImpl = new BirdDaoImpl();

	public List<Bird> getAllBirds() {
		return birdDaoImpl.getAllBirds();
	}

	public Bird getBirdById(int id) {
		return birdDaoImpl.getBirdById(id);
	}

	public int createBird(Bird bird) {
		return birdDaoImpl.createBird(bird);
	}

	public List<Bird> getBirdsByBreed(String breedParam) {
		return getAllBirds().stream().filter(b -> b.getBreed().equalsIgnoreCase(breedParam))
				.collect(Collectors.toList());
	}
}
