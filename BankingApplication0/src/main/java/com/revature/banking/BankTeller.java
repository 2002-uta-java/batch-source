package com.revature.banking;

public abstract class BankTeller {
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
		}
	}

	protected abstract void userLogin(String... args) throws TransactionException;
}
