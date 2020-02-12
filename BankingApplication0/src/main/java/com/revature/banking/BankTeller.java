package com.revature.banking;

import java.util.List;

public abstract class BankTeller {
	public static final int USER_LOGIN = 0;
	public static final int CREATE_ACCOUNT = 1;
	public static final int VIEW_ACCOUNTS = 2;
	public static final int ADD_FUNDS = 3;

	/**
	 * Performs the requested transaction with the given string arguments.
	 * 
	 * @param transaction Integer representing the type of transaction to be
	 *                    performed
	 * @param args        The arguments needed to perform the given transaction.
	 * @return Returns a list of strings as the "output" (if there is any).
	 * @throws TransactionException Thrown if the transaction cannot be performed. A
	 *                              message should be included explaining why the
	 *                              transaction failed.
	 */
	public List<String> doTransaction(int type, String... args) throws TransactionException {
		switch (type) {
		case USER_LOGIN:
			return userLogin(args);
		case CREATE_ACCOUNT:
			return createAccount(args);
		case ADD_FUNDS:
			return addFunds(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
		case VIEW_ACCOUNTS:
			return viewAccounts();
		default:
			throw new TransactionException("This operation isn't supported");
		}
	}

	/**
	 * This method should return a list of Strings that represent the state of the
	 * currently logged on user's accounts.
	 * 
	 * @return
	 */
	protected abstract List<String> viewAccounts();

	/**
	 * This should add the amount specified to the account specified for this user.
	 * 
	 * @param account The index of the account (for this user) to add funds to.
	 * @param amount  The amount to add to the specified account.
	 * @return Returns a singleton list containing the information for the modified
	 *         account.
	 * @throws TransactionException
	 */
	protected abstract List<String> addFunds(int account, final double amount) throws TransactionException;

	protected abstract List<String> createAccount(String[] args) throws TransactionException;

	protected abstract List<String> userLogin(String[] args) throws TransactionException;
}
