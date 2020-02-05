package com.charnecki.life;

public class IsDeadException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IsDeadException() {
		super();
	}
	
	public IsDeadException(String message) {
		super(message);
	}
	
}
