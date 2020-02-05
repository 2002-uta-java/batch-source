package com.revature.exercise;

public class Dog extends Mammals {
	
	// Unique features of dog:
	// can have a name, noise it makes, eats food uniquely
	
	private String name;
	
	public Dog(String color, int age, String name) {
		super();
		setColor(color);
		setAge(age);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	@Override
	public void makeNoise() {
		System.out.println("Bark!");
	}
	
	@Override
	public void eatFood() {
		System.out.println("*aggressive chewing noises*");
	}
	
	@Override
	public String toString() {
		return "Dog [getColor()=" + getColor() + ", getAge()=" + getAge() + ", getName()=" 
	+ getName() + "]";
	}
	
	@Override
	public boolean equals(Object mammalCompare) {
		if (this.hashCode() == mammalCompare.hashCode()) {
			return true;	  
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return 1;
	}

}
