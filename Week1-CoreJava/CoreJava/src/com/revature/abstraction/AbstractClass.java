package com.revature.abstraction;

public abstract class AbstractClass {
	
	//can include both concrete and abstract methods
	//can include instance variables
	
	public AbstractClass() {
		super();
	}
	
	public abstract void myAbstractMethod();
	
	public void myConcreteMethod() {
		System.out.println("we can still have implementation here");
	}

}
