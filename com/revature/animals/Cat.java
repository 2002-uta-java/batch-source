package com.revature.animals;



public class Cat extends Animal {

	public Cat() {
		super();
	}
	
	public Cat(String name, String noise) {
		super(name, noise);
	}
	
	// Method overridden the abstract method from Animal
	@Override
	void makeNoise() {
		// TODO Auto-generated method stub
		System.out.println("Go away. I'm not going to " + getNoise() + ".");
	}

	// Inherit reproduce behavior from animal
	public String reproduce() {
		return "Kitties";
	}
}
