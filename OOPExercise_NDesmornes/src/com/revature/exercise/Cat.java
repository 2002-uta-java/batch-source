package com.revature.exercise;

public class Cat extends Mammals {
	
	// Unique features of cat:
	// can have a name, sleep time, noise it makes, eats food uniquely
	
	private String name;
	private int sleepLength; // in hours
	
	public Cat(String color, int age, String name, int sleepLength) {
		super();
		setColor(color);
		setAge(age);
		this.name = name;
		this.sleepLength = sleepLength;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getSleepTime() {
		return this.sleepLength;
	}
	
	public void setSleepTime(int newSleepLength) {
		this.sleepLength = newSleepLength;
	}
	
	@Override
	public void makeNoise() {
		System.out.println("Meow!");
	}
	
	@Override
	public void eatFood() {
		System.out.println("*meek chewing noises*");
	}
	
	@Override
	public String toString() {
		return "Cat [getColor()=" + getColor() + ", getAge()=" + getAge() + ", getName()=" 
	+ getName() + ", getSleepTime()=" + getSleepTime() + "]";
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
		return 2;
	}
}
