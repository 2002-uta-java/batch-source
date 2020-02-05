package com.hylicmerit;

/*this class demonstrates run time and compile time polymorphism through overloading
  and overriding*/
public class JuniorDeveloper extends Developer{
	public JuniorDeveloper() {
		super();
		this.role = "Jr Developer";
	}
	@Override
	public void work() {
		System.out.println(this.role + " " + this.name + " is working on testing the product");
	}
	//overloading takeBreak method from parent by printing a specific break time
	public void takeBreak(int min) {
		System.out.println(this.role + " " + this.name + " is taking a " + min + " minute break.");
	}
}
