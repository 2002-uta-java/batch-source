package com.charnecki.life;

public class NegativeAgeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NegativeAgeException() {
		super("Age cannot be a negative number.");
	}
	
}
