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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sound == null) ? 0 : sound.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crow other = (Crow) obj;
		if (sound == null) {
			if (other.sound != null)
				return false;
		} else if (!sound.equals(other.sound))
			return false;
		return true;
	}
}
