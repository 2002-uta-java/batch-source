package com.revature.cuisines;

public class Asian extends Cuisine{
	public Asian() {
		super();
	}
	
	// overloading constructor allows for object to use different signatures when constructing
	public Asian(String food) {
		this.food = food;
		utensil = "chopsticks";
	}
	
	public void cook() {
		System.out.println("Cook me with soy sauce!");
	}
	
	public void eat() {
		System.out.println("Eat me with " + super.utensil);
	}
	
	public String getFood() {
		return food;
	}

}
