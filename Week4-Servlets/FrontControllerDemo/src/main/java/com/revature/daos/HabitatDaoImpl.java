package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Habitat;

public class HabitatDaoImpl implements HabitatDao {
	
	private List<Habitat> habitats = new ArrayList<>();
	
	public HabitatDaoImpl() {
		super();
		habitats.add(new Habitat(1, "Woodlands", 60, 520));
//		habitats.add(new Habitat(2, "Tropical", 85, 350));
		habitats.add(new Habitat(3, "Grasslands", 65, 350));
		
	}

	@Override
	public List<Habitat> getAllHabitats() {
		return new ArrayList<>(habitats);
	}

	@Override
	public Habitat getHabitatById(int id) {
		for(Habitat habitat: habitats) {
			if(habitat.getId() == id) {
				return habitat;
			}
		}
		return null;
	}

}
