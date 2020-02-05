package com.revature.pillarsAssignment;

public class CustomException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Create a new exception to throw
	public CustomException() {
		System.out.println("The custom exception");
	}
}
