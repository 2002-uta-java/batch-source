package com.hylicmerit;

import java.lang.reflect.Method;

public class Driver{
	public static void main(String[] args) throws IncorrectNameException {
		JuniorDeveloper lula = new JuniorDeveloper();
		lula.setName("Lula");
		lula.work();
		lula.takeBreak(30);
		lula.promote("Senior Developer");
		
		SeniorDeveloper mila = new SeniorDeveloper();
		mila.setName("Mila");
		mila.work();
		mila.takeBreak(60);
		
		//this demonstrates polymorphism through covariant types
		Developer matt = new JuniorDeveloper();
		String name = "Matt";
		matt.setName(name);
		matt.work();
		matt.takeBreak();
		
		//print hash codes of methods in Developer class
		@SuppressWarnings("rawtypes")
		Class c = Developer.class;
		Method[] methods = c.getMethods();
		for(Method m: methods) {
			System.out.println("Hash code for the method " + 
					m.getName() + " is " + m.hashCode());
		}
		
		//compare the instance's name with the local variable
		if(matt.getName().equals(name)) {
			System.out.println("The instance name and local name are the same");
		}
		
		//tests out the custom exception
		matt.setName(null);
	}
}
