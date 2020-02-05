package com.hylicmerit;

//this class demonstrates inheritance by extending from a parent class
public class Developer extends Employee{
	public Developer() {
		super();
		this.role = "Developer";
		this.name = "";
	}
	private boolean checkName(String name) throws IncorrectNameException 
    { 
        if (name == null) 
            throw new IncorrectNameException("The argument cannot be null"); 
        return true; 
    } 
	public void setName(String name) throws IncorrectNameException {
		//use custom exception to check if name is null
		try {
			if(checkName(name)) {
				this.name = name;
			}
		}
		catch(NullPointerException e) {
			throw new IncorrectNameException(e.getMessage());
		}
	}
	public String getName() {
		return this.name;
	}
	/*this method will be overriden by it's children classes when they 
	 * have a specific developer role*/
	public void work() {
		System.out.println(this.role + " "  + this.name + " is working on developing a product");
	}
	//this method will be overloaded in it's children classes
	public void takeBreak() {
		System.out.println(this.role + " " + this.name + " is taking a 30 minute break.");
	}
}
