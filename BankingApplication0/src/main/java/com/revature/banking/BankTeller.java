package com.revature.banking;

public abstract class BankTeller {
	public static final int USER_LOGIN = 0;
	public static final int CREATE_ACCOUNT = 1;

	/**
	 * Performs the requested transaction with the given string arguments.
	 * 
	 * @param transaction Integer representing the type of transaction to be
	 *                    performed
	 * @param args        The arguments needed to perform the given transaction.
	 * @throws TransactionException Thrown if the transaction cannot be performed. A
	 *                              message should be included explaining why the
	 *                              transaction failed.
	 */
	public void doTransaction(int type, String... args) throws TransactionException {
		switch (type) {
		case USER_LOGIN:
			userLogin(args);
			break;
		case CREATE_ACCOUNT:
			createAccount(args);
			break;
		}
	}

	protected abstract void createAccount(String[] args) throws TransactionException;

	protected abstract void userLogin(String... args) throws TransactionException;
}
