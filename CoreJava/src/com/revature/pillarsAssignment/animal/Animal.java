package com.revature.pillarsAssignment.animal;

public abstract class Animal {
	// The abstract class for Animal
	private String food;
	int legs;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + legs;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (legs != other.legs)
			return false;
		return true;
	}
	// Polymorphism through Overloading
	public Animal() {
		// create an animal
	}
	public Animal(String food, int legs) {
		this.setFood(food);
		this.legs = legs;
	}
	
	// Abstract method for each animal to implement
	public abstract void eat();
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
}
