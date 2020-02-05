package com.revature.exercise;

import com.revature.exceptions.*;

public abstract class Mammals implements Behavior {
	
	// All mammals possess these features:
	// warm blooded, furry/hairy, has color, has age
	// also possesses behaviors
	
	private static boolean isWarmBlooded = true; 
	private static boolean hasFur = true;
	
	private String color;
	private int age; // in years
	
	public Mammals() {
		super();
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String newColor) {
		this.color = newColor;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int newAge) {
		// The age of any mammal cannot be negative. If you try to set the age to
		// a negative number, a CUSTOM EXCEPTION will be thrown. 
		if (newAge >= 0) {
			this.age = newAge;
		}
		else {
			try {
				throw new NegativeAgeException("Age cannot be negative.");
			}
			catch (NegativeAgeException e) {
				e.printStackTrace();
			}
		}
	}
	
}
