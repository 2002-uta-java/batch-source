package com.revature.daos;

import java.util.List;

import com.revature.models.Bird;

public interface BirdDao {

	public List<Bird> getAllBirds();
	public Bird getBirdById(int id);
	public int createBird(Bird bird);
	
}