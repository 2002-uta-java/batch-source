package com.revature.construction;

// This is the custom exception.  It can be thrown from either method
// in the Buildable interface.

public class ConstructionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConstructionException() {
		super();
	}
	
	public ConstructionException(String message) {
		super(message);
	}

}
