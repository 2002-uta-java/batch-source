package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Bird;
import com.revature.models.Habitat;

public class BirdDaoImpl implements BirdDao {
	
	private List<Bird> birds = new ArrayList<>();
	
	public BirdDaoImpl() {
		birds.add(new Bird(1,"Tweety",50,"Canary",new Habitat(1, "Woodlands", 60, 520)));
		birds.add(new Bird(2, "Fluffy", 20, "Crow", new Habitat(1, "Woodlands", 60, 520)));
		birds.add(new Bird(3, "Sam", 40, "Toucan", new Habitat(2, "Tropical", 85, 350)));
	}

	@Override
	public List<Bird> getAllBirds() {
		return new ArrayList<>(birds);
	}

	@Override
	public Bird getBirdById(int id) {
		for(Bird bird: birds) {
			if(bird.getId() == id) {
				return bird;
			}
		}
		return null;
	}

	@Override
	public int createBird(Bird bird) {
		int maxId = 0; 
		for(Bird b: birds) {
			if(b.getId()>maxId) {
				maxId = b.getId();
			}
		}
		bird.setId(maxId+1);
		birds.add(bird);
		return maxId+1;
	}

}
