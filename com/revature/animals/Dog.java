package com.revature.animals;

import java.util.Objects;

public class Dog extends Animal implements Fetchable {

	public Dog() {
		super();
	}
	
	public Dog(String name, String noise) {
		super(name, noise);
	}
	
	// Method overridden the abstract method from Animal
	@Override
	void makeNoise() {
		System.out.println(getNoise() + "!!!");
	}

	// Method overloading for reusability of same method name with different implementation 
	public void fetch() {
		System.out.println("Fetching!");
	}
	
	public void fetch(String item) {
		System.out.println("Fetching the " + item.trim() + "!");
	}
	
	// Inherit reproduce behavior from animal
	public String reproduce() {
		return "Puppies";
	}

	public boolean equals(Object d) {
		if(d == null) {
			return false;
		}
		
		if(d instanceof Dog) {
			Dog dog = (Dog) d;
			return dog.getName() == this.getName() && 
					dog.getNoise() == this.getNoise() &&
					dog.getAge() == this.getAge();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return Objects.hash(this.getName(), this.getNoise(),this.getAge());
	}
}
