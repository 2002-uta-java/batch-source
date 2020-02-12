package com.revature.bank.exceptions;

public class IllegalTransactionException extends Exception {

	/**
	 * generate by eclipse
	 */
	private static final long serialVersionUID = 2535588608021943611L;

	/**
	 * Default constructor with no message.
	 */
	public IllegalTransactionException() {
		super();
	}

	/**
	 * Constructor with message (just calls new Exception(String message)).
	 * 
	 * @param message message explaining this IllegalTransaction.
	 */
	public IllegalTransactionException(final String message) {
		super(message);
	}

}
