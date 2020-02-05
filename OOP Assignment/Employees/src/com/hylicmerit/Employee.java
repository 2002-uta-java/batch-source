package com.hylicmerit;

//this class demonstrates abstraction by only setting the blueprint for its children
public abstract class Employee {
	//the employee's name will be available in extended classes
	protected String name;
	//the employee's role will be available in extended classes
	protected String role;
	//the work will be implemented in its children classes
	abstract void work();
	//the takeBreak method will be implemented in its children classes
	abstract void takeBreak();
}
