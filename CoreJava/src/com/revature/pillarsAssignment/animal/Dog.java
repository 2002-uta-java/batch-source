package com.revature.pillarsAssignment.animal;

public class Dog extends Animal implements Eats {

	public Dog(String food, int legs) {
		super(food, legs);
	}
	
	// Use eat from Eats to implement method
	@Override
	public void eat() {
		
		// Use of equal method to check if dogs food is same as animals food
		if (super.getFood().equals(this.getFood())) {
			Eats.eat();
		}
	}

}
