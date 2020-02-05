package com.revature.animals;

// Abstraction to allow Dog and Cat to write their own implementations
public abstract class Animal implements Reproducible {

	// Encapsulate name and noise fields
	private String name;
	private String noise;
	private int age;

	public Animal() {
		super();
	}
	
	// Constructor overloading to allow 0 or 2 parameters
	public Animal(String name, String noise) {
		this.name = name;
		this.noise = noise;
	}
	
	abstract void makeNoise();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoise() {
		return noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) throws MyCustomException {
		if (age <= 0) {
			throw new MyCustomException();
		}
		this.age = age;
	}
}
