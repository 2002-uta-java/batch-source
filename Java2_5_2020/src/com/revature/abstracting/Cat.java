package com.revature.abstracting;

public class Cat extends AbstractAnimal implements Behaving{

	
	//private String color = "Black";
	

	@Override
	public void MakesNoise() {
		System.out.println("The Cat is Meowing!");
		
	}

	@Override
	public String Eating(String food) {
		System.out.println("The cat is eating " + food);
		return null;
	}

	@Override
	public String Eating(String food1, String food2) {
		System.out.println("The cat is eating " + food1 + " and " + food2);
		return null;
	}
}
