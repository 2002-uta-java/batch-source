package com.revature.abstraction;

public interface InterfaceA {
	
	int MY_INT = 6; //implicitly public static final
	
	void doSomething();
	
	default void doSomethingElse() {
		System.out.println("InterfaceA is doing something else");
	}

}
