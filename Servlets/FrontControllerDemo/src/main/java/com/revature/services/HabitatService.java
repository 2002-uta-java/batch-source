package com.revature.services;

import java.util.List;

import com.revature.daos.HabitatDao;
import com.revature.daos.HabitatDaoImpl;
import com.revature.models.Habitat;

public class HabitatService {
	
	private HabitatDao habitatDao = new HabitatDaoImpl();
	
	public List<Habitat> getAllHabitats(){
		return habitatDao.getAllHabitats();
	}
	
	public Habitat getHabitatById(int id) {
		return habitatDao.getHabitatById(id);
	}
	
}