package com.revature.oopexercise;

public abstract class Animal {
	public abstract void makeSound();
	
	public void eat() {
		System.out.println("Eating...");
	}
	public void die() {
		System.out.println("...it dies");
	}
	
}
