package com.Revature.Animals;

public class FurType {

	boolean hasFur;
	
	public FurType() { 
	super();
	}
	
	public boolean getHasFur() { // stores the value for the boolean variable hasFur using get and set
		return hasFur;
	}
	public boolean setFurType() {
		return hasFur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasFur ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FurType other = (FurType) obj;
		if (hasFur != other.hasFur)
			return false;
		return true;
	}
}