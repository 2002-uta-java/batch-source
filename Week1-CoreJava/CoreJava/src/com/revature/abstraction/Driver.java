package com.revature.abstraction;

public class Driver {
	
	public static void main(String[] args) {
		ConcreteClass cc = new ConcreteClass();
		cc.doSomething();
		cc.doSomethingElse();
		System.out.println(InterfaceA.MY_INT);
		cc.myAbstractMethod();
	}

}
