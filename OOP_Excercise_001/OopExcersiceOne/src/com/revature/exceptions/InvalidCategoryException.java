package com.revature.exceptions;

public class InvalidCategoryException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCategoryException() {
		super();
	}
	
	public InvalidCategoryException(String message) {
		super(message);
		
	}
}
