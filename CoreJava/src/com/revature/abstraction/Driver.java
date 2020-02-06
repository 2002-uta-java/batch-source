package com.revature.abstraction;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcreteClass cc = new ConcreteClass();
		cc.doSomething();
		cc.doSomethingElse();
		System.out.println(InterfaceA.MY_INT);
		cc.myAbstractMethod();

	}

}
