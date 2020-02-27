package com.revature.daos;

import java.util.List;

import com.revature.models.Habitat;

public interface HabitatDao {
	
	public List<Habitat> getAllHabitats();
	public Habitat getHabitatById(int id);

}