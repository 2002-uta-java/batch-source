package com.chai.sean.exceptions;

//Inheritance through Extends

public class GreaterThanTwoEdgeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GreaterThanTwoEdgeException() {
		super();
	}
	
	public GreaterThanTwoEdgeException(String message) {
		
		super(message);
	}
}
