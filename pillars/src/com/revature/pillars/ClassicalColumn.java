package com.revature.pillars;

import com.revature.construction.ConstructionException;

public class ClassicalColumn extends Column {
	public static enum COLUMN_TYPE { DORIC, IONIC, CORINTHIAN };
	public COLUMN_TYPE type;
	
	public ClassicalColumn(COLUMN_TYPE type) {
		this.type = type;
	}
	
	public String toString() {
		return "classical " + type + " column";
	}
	
	/* This reimplementation of raze is an example of method overriding
	 * which is an expression of polymorphism.
	 */
	@Override
	public void raze() throws ConstructionException {
		throw new ConstructionException("History must be preserved");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ClassicalColumn other = (ClassicalColumn) obj;
		if (type != other.type)
			return false;
		return true;
	}
}
