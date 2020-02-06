package com.Revature.Animals;

import com.Revature.exceptions.NegativeHeight;

public class Dog extends FurType {

	protected double height; // example of Encapsulation because these variables can not be accessed 
	protected boolean hasFur; // unless they are extended in a different class (in this case the Human class)
	
	public Dog() {
		super(); // example of Inheritance since the no argument constructor 
	}			 // is making a call to the super class constructor, which in this case is the object in Java
	
	public Dog(double height, boolean hasFur) {
		this();
		this.height = height; // this keyword helps with Inheritance since the two variables 
		this.hasFur = hasFur; // are declared in the same scope with the same name
	}
	
	// getter for height
	public double getHeight() {
		return height;
	}

	public void setHeight1(double height) {
		if (this.height <= 0) 
		try {
		throw new NegativeHeight("The Height is less than 0" );
	} catch (NegativeHeight e) {
		e.printStackTrace();
	}
}
	// setter for height
	public void setHeight(double height) {
		this.height = height;
	}

//getter for fur
	public boolean gethasFur() {
		return hasFur;
	}

//setter for fur
	public void setHasFur(boolean hasFur) {
		this.hasFur = hasFur;
	}
	
	@Override
	public String toString() {
		return "Dog's height = " + height + " Feet, Does it have Fur? " + hasFur + "";
	}

	public String calculateHeight() { // uses the double value to determine if the respective animal is tall or short
		if (height < 3.0)
			return "The (Dog) Animal is Short";
		else
			return "The (Dog) Animal is Tall";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (hasFur ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (hasFur != other.hasFur)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		return true;
	}

	
}
