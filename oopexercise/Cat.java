package com.revature.oopexercise;

public class Cat extends Animal implements Lifeform {
	private String sound;
	
	@Override
	public void eat() {
		System.out.println("Cat eating mice...");
	}
	
	public void makeSound() {
		System.out.println(sound);
	}
	
	public void setSound(String newSound) {
		this.sound = newSound;
	}
	
}
