package com.revature.exercise;

public class Horse extends Mammals {
	
	// Unique features of horse:
	// has top running speed, noise it makes, eats food uniquely
	
	private int topSpeed;
	
	public Horse(String color, int age, int topSpeed) {
		super();
		setColor(color);
		setAge(age);
		this.topSpeed = topSpeed;
	}
	
	public int getTopSpeed() {
		return this.topSpeed;
	}
	
	public void setTopSpeed(int newTopSpeed) {
		this.topSpeed = newTopSpeed;
	}
	
	@Override
	public void makeNoise() {
		System.out.println("*Neighs*");
	}
	
	@Override
	public void eatFood() {
		System.out.println("*indecent chewing noises*");
	}
	
	@Override
	public String toString() {
		return "Horse [getColor()=" + getColor() + ", getAge()=" + getAge() + ", getTopSpeed()=" 
	+ getTopSpeed() + "]";
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
		return 3;
	}
}
