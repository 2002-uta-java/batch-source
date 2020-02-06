package com.Revature.exceptions;

public class NegativeHeight extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NegativeHeight() {
		super();
	}

	public NegativeHeight(String message) {
		super(message);
	}
}
