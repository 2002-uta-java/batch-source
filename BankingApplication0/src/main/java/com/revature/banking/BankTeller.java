package com.revature.banking;

import java.util.List;

public abstract class BankTeller {
	public static final int USER_LOGIN = 0;
	public static final int CREATE_ACCOUNT = 1;
	public static final int VIEW_ACCOUNT_BALANCES = 2;
	public static final int ADD_FUNDS = 3;
	public static final int VIEW_STATEMENT = 4;
	public static final int CLOSE_ACCOUNT = 5;
	public static final int DELETE_USER = 6;
	public static final int USER_LOGOUT = 7;
	public static final int TRANSFER_FUNDS = 8;

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
		case USER_LOGOUT:
			return userLogout();
		case CREATE_ACCOUNT:
			return createAccount(args);
		case ADD_FUNDS:
			return addFunds(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
		case VIEW_ACCOUNT_BALANCES:
			return viewAccounts();
		case VIEW_STATEMENT:
			return viewStatement(Integer.parseInt(args[0]));
		case CLOSE_ACCOUNT:
			return closeAccount(Integer.parseInt(args[0]));
		case DELETE_USER:
			return deleteUser();
		case TRANSFER_FUNDS:
			return transferFunds(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
		default:
			throw new TransactionException("This operation isn't supported");
		}
	}

	/**
	 * Transfers funds between two accounts (owned by the same, logged in, user).
	 * 
	 * @param withdrawAccount 0-indexed value of account to withdraw from.
	 * @param transferAccount 0-indexed value of account to transfer to.
	 * @param amount          Amount to transfer.
	 * @return List of the two accounts that were modified.
	 */
	protected abstract List<String> transferFunds(int withdrawAccount, int transferAccount, double amount);

	/**
	 * Logs user out (discards any data needed to interact with this user)
	 * 
	 * @return null (nothing to return)
	 */
	protected abstract List<String> userLogout();

	/**
	 * Deletes user from system (returns nothing).
	 * 
	 * @return
	 */
	protected abstract List<String> deleteUser();

	/**
	 * Close (deletes) the specified account and returns a list of updated accounts
	 * (or an empty list if there are no others).
	 * 
	 * @param account 0-indexed account to be closed and removed from the user's
	 *                accounts
	 * @return List of remaining accounts (or an empty list if none remain).
	 */
	protected abstract List<String> closeAccount(int account);

	/**
	 * Return a List of strings representing the specified accounts bank statements
	 * (withdrawals and deposits),
	 * 
	 * @param account An index into the list of accounts this user has (0-indexed).
	 * @return List of Strings representing transactions on this account given in
	 *         the order they happened
	 */
	protected abstract List<String> viewStatement(final int account);

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
