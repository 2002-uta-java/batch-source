package com.revature.cuisines;

import java.util.Arrays;
import java.util.List;

public abstract class Cuisine implements Feedable {
	protected List<String> utensilList = Arrays.asList("chopsticks", "fork", "spoon", "hands", "knife");

	// protected instance variable enables encapsulation
	protected String food;
	protected String utensil;
	
	// super shows inheritance in the Java language
	public Cuisine() {
		super();
	}
	
	// getters and setters prevent tampering with instance variables
	// abstract method enables abstraction, holding off on implementation until later
	abstract String getFood();
	void setUtensil(String utensil) {
		if(utensilList.contains(utensil)) {
			this.utensil = utensil;
		} else {
			try {
				throw new UtensilException(utensil + " is not a usable utensil.");
			}
			catch(UtensilException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuisine other = (Cuisine) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((utensil == null) ? 0 : utensil.hashCode());
		return result;
	}
}
