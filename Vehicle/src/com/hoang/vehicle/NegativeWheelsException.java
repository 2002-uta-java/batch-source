package com.hoang.vehicle;

public class NegativeWheelsException extends Exception{
	private static final long serialVersionUID = 1L;
		
		public NegativeWheelsException() {
			super();
		}
		
		public NegativeWheelsException(String message) {
			super(message);
		}
}
