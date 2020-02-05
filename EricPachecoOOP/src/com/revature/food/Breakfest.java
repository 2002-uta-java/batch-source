package com.revature.food;

public interface Breakfest {
	int TIME_EATEN = 8;
	String MOOD = "tired";
	
	default void currentMood() {
		System.out.println("I'm so tired!");
	}
	void dayReady();
}
