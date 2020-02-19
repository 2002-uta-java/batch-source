package com.revature.cuisines;

public class American extends Cuisine {
	
	public American() {
		super();
	}
	
	public American(String food) {
		this.food = food;
		utensil = "hands";
	}
	
	public void cook() {
		System.out.println("Cook me with oil!");
	}
	
	public void eat() {
		System.out.println("Eat me with your " + utensil);
	}
	
	public String getFood() {
		return food;
	}
}
