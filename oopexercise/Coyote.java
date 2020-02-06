package com.revature.oopexercise;

public class Coyote extends Animal implements Lifeform {
	private String sound;
	
	@Override
	public void eat() {
		System.out.println("Coyote eating garbage...");
	}
	public void eat(String food) {
		System.out.println("Coyote eating " + food + "...");
	}
	
	public void makeSound() {
		System.out.println(sound);
	}
	
	public void setSound(String newSound) {
		this.sound = newSound;
	}
	
}
