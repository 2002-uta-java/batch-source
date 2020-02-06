package com.chai.sean.exceptions;

//Inheritance through Extends

public class ZeroOrLessBevelException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ZeroOrLessBevelException() {
		super();
	}
	
	
	public ZeroOrLessBevelException(String message) {
		
		super(message);
	}

}
