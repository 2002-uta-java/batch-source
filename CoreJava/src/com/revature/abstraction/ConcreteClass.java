package com.revature.abstraction;

public class ConcreteClass extends AbstractClass implements InterfaceA, InterfaceB {

	@Override
	public void doSomething() {
		System.out.println("Concrete class is doing something");
	}
	
	@Override
	public void myAbstractMethod() {
		System.out.println("Not so abstract anymore");
	}
	@Override 
	public void doSomethingElse() {
		InterfaceA.super.doSomethingElse();
	} 
}
