package com.revature.clothing;

//In this class we see examples of Inheritance and Polymorphism

public class Pants extends Bottoms {
	
	public Pants() {
		this.setCategory("pants");
		System.out.println("You created a new pair of pants, here are the details:");
		System.out.println(this.toString());
	}
	
	// here is an example of overloading - a polymorphism concept
	public Pants(String brand, String color, String material) {
		this.setBrand(brand);
		this.setColor(color);
		this.setMaterial(material);
		this.setCategory("pants");
		System.out.println("You created a new pair of pants, here are the details:");
		System.out.println(this.toString());
	}

	// the following methods override methods - a polymorphism concept
	// the putOn() and takeOff() are inherited from the Wearable interface
	// these were overwitten and implemented at this level so that they are specific to the clothing item in question
	@Override
	public void putOn() {
		if(!status) {
			System.out.println("You put on pants... Looking good!");
			wearStatus(true);
		} else {
			System.out.println("Don't be silly, you already wearing these");
		}
	}

	@Override
	public void takeOff() {
		if(status) {
			System.out.println("You take off your pants.");
			wearStatus(false);
		} else {
			System.out.println("You are not wearing these pants!");
		}
	}
}