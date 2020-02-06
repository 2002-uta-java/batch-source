package com.revature.oopexercise;

public class Crow extends Animal implements Lifeform {
	private String sound;
	
	@Override
	public void eat() {
		System.out.println("Crow eating roadkill...");
	}
	
	public void makeSound() {
		System.out.println(sound);
	}
	
	public void setSound(String newSound) {
		this.sound = newSound;
	}
}
