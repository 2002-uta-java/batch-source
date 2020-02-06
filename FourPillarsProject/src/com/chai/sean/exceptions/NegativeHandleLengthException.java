package com.chai.sean.exceptions;

//Inheritance through Extends

public class NegativeHandleLengthException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8380702820057112604L;

	public NegativeHandleLengthException(String message) {
		
		super(message);
	}

}
