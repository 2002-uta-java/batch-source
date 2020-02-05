package com.revature.cuisines;

// extending Cuisine enables inheritance, allowing for similar functionality as the parent class
public class Chinese extends Asian {
	
	public Chinese() {
		super();
	}
	
	public Chinese(String food) {
		super(food);
	}
	
	// overriding the cook method enables polymorphic ability by varying object behaviors depending on context
	public void cook() {
		System.out.println("Cook me with soy sauce and add some ginger!");
	}
	
	//overloading method also shows polymorphic ability
	public void cook(String... ingredients) {
		System.out.println("Throw all the ingerdients in! I'm a stir fry!");
	}
}
