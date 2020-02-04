package com.revature.abstraction;

public interface InterfaceB {
	
	default void doSomething() {
		System.out.println("doing something in interfaceB");
	}

	default void doSomethingElse() {
		System.out.println("InterfaceB is doing something else");
	}
}
