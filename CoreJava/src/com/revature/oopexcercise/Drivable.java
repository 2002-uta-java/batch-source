package com.revature.oopexcercise;

public interface Drivable {
	public void drivable();
	
	int MY_INT = 4; //implicitly public static final
	
	void doSomething();
	
	//An interface/action a user can take when operating a car
	default void drivableObject() {
		System.out.println("Drivable is an action a user can make to the car do something.");
	}
	
}
