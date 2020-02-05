package com.revature.exceptions;

public class NegativeAgeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NegativeAgeException() {
		super();
	}
	
	public NegativeAgeException(String message) {
		super(message);
	}
	
}
