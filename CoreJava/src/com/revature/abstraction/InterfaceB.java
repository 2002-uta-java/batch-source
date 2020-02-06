package com.revature.abstraction;

public interface InterfaceB {
	default void doSomething() {
		System.out.println("do something in InterfaceB");
	}
	
	default void doSomethingElse() {
		System.out.println("InterfaceB is doing something else");

	}
}
