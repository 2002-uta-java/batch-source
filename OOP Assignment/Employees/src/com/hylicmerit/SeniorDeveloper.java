package com.hylicmerit;

public class SeniorDeveloper extends Developer{
	public SeniorDeveloper() {
		super();
		this.role = "Senior Developer";
	}
	@Override
	public void work() {
		System.out.println(this.role + " " + this.name + " is working on a major feature for the product");
	}
	//overloading takeBreak method from parent by printing a specific break time
	public void takeBreak(int min) {
		System.out.println(this.role + " " + this.name + " is taking a " + min + " minute break.");
	}
}
