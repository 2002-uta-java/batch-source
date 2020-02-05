package com.revature.pillarsAssignment.animal;

// This class inherits from Animal food and legs
public class Cat extends Animal {
	public Cat(String food, int legs) {
		super(food, legs);
	}
	
	// Polymorphism through Overriding
	@Override
	public void eat() {
		System.out.println("The cat is eating!");
	}
}
