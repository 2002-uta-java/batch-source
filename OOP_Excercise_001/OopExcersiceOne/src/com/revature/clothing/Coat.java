package com.revature.clothing;

//In this class we see examples of Inheritance and Polymorphism

public class Coat extends Tops{
	
	public Coat() {
		this.setCategory("coat");
		System.out.println("You created a new coat, here are the details:");
		System.out.println(this.toString());
	}
	
	// here is an example of overloading - a polymorphism concept
	public Coat(String brand, String color, String material) {
		this.setBrand(brand);
		this.setColor(color);
		this.setMaterial(material);
		this.setCategory("coat");
		System.out.println("You created a new coat, here are the details:");
		System.out.println(this.toString());
	}

	// the following methods override methods - a polymorphism concept
	// the putOn() and takeOff() are inherited from the Wearable interface
	// these were overwitten and implemented at this level so that they are specific to the clothing item in question
	@Override
	public void putOn() {
		if(!status) {
			System.out.println("You put on a coat... You feel warm.");
			wearStatus(true);
		} else {
			System.out.println("Nope... You already have the coat on.");
		}
	}

	@Override
	public void takeOff() {
		if(status) {
			System.out.println("You take off your coat... You feel cold");
			wearStatus(false);
		} else {
			System.out.println("You would, if you were wearing the coat!");
		}
	}
}