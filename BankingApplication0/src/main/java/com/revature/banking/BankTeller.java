package com.revature.banking;

import java.util.List;

public interface BankTeller {
//	public static final int USER_LOGIN = 0;
//	public static final int CREATE_ACCOUNT = 1;
//	public static final int VIEW_ACCOUNT_BALANCES = 2;
//
//	public static final int ADD_FUNDS = 3;
//	public static final int VIEW_STATEMENT = 4;
//	public static final int CLOSE_ACCOUNT = 5;
//	public static final int DELETE_USER = 6;
//	public static final int USER_LOGOUT = 7;
//	public static final int TRANSFER_FUNDS = 8;
//	public static final int OPEN_NEW_ACCOUNT = 9;
//	public static final int WITHDRAW_FUNDS = 10;
//
//	public static final int CHECK_USER_NAME = 11;
//	public static final int CHECK_PASSWORD = 12;

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
//	public List<String> doTransaction(int type, String... args) throws TransactionException {
//		switch (type) {
//		case USER_LOGIN:
//			return userLogin(args[0], args[1]);
//		case USER_LOGOUT:
//			return userLogout();
//		case CREATE_ACCOUNT:
//			return createAccount(args[0], args[1], args[2], args[3], args[4]);
//		case ADD_FUNDS:
//			return addFunds(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
//		case VIEW_ACCOUNT_BALANCES:
//			return viewAccounts();
//		case VIEW_STATEMENT:
//			return viewStatement(Integer.parseInt(args[0]));
//		case CLOSE_ACCOUNT:
//			return closeAccount(Integer.parseInt(args[0]));
//		case DELETE_USER:
//			return deleteUser();
//		case TRANSFER_FUNDS:
//			return transferFunds(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
//		case OPEN_NEW_ACCOUNT:
//			return openNewAccount();
//		case WITHDRAW_FUNDS:
//			return withdrawFunds(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
//		case CHECK_USER_NAME:
//			return checkUserName(args[0]);
//		case CHECK_PASSWORD:
//			return checkPassword(args[0]);
//		default:
//			throw new TransactionException("This operation isn't supported");
//		}
//	}

	/**
	 * Checks to see if the password is valid. A valid password will return null
	 * from this method (no error message).
	 * 
	 * @param password password to be checked
	 * @return A String that represents an explanation for why this password is
	 *         invalid or null if it is a valid password.
	 */
	public abstract String checkPassword(String password);

	/**
	 * Check to see if username is valid. Returns a String explaining why it's
	 * invalid or null if it is valid.
	 * 
	 * @param username Username to check
	 * @return Null if username is valid or a String explaining why it's invalid.
	 */
	public abstract String checkUserName(String username);

	/**
	 * Withdraws the amount specified from the specified account.
	 * 
	 * @param account    0-indexed selection from list of accounts user has.
	 * @param withdrawal amount to withdraw
	 * @return A string representing the information for the account after the
	 *         withdrawal.
	 * @throws TransactionException
	 */
	public abstract String withdrawFunds(int account, double withdrawal) throws TransactionException;

	/**
	 * Creates a new account for currently logged on user.
	 * 
	 * @return Returns the new bank account (its balance will be zero). This should
	 *         return the full bank account number.
	 */
	public abstract String openNewAccount() throws TransactionException;

	/**
	 * Transfers funds between two accounts (owned by the same, logged in, user).
	 * 
	 * @param withdrawAccount 0-indexed value of account to withdraw from.
	 * @param transferAccount 0-indexed value of account to transfer to.
	 * @param amount          Amount to transfer.
	 * @return List of the two accounts that were modified.
	 */
	public abstract List<String> transferFunds(int withdrawAccount, int transferAccount, double amount)
			throws TransactionException;

	/**
	 * Logs user out (discards any data needed to interact with this user).
	 */
	public abstract void userLogout() throws TransactionException;

	/**
	 * Deletes user from system.
	 */
	public abstract void deleteUser() throws TransactionException;

	/**
	 * Close (deletes) the specified account and returns a list of updated accounts
	 * (or an empty list if there are no others).
	 * 
	 * @param account 0-indexed account to be closed and removed from the user's
	 *                accounts
	 * @return List of remaining accounts (or an empty list if none remain).
	 */
	public abstract List<String> closeAccount(int account) throws TransactionException;

	/**
	 * Return a List of strings representing the specified accounts bank statements
	 * (withdrawals and deposits),
	 * 
	 * @param account An index into the list of accounts this user has (0-indexed).
	 * @return List of Strings representing transactions on this account given in
	 *         the order they happened
	 */
	public abstract List<String> viewStatement(final int account) throws TransactionException;

	/**
	 * This method should return a list of Strings that represent the state of the
	 * currently logged on user's accounts.
	 * 
	 * @return A list of Strings representing each of the user's accounts.
	 * @throws TransactionException
	 */
	public abstract List<String> viewAccounts() throws TransactionException;

	/**
	 * This should add the amount specified to the account specified for this user.
	 * 
	 * @param account The index of the account (for this user) to add funds to.
	 * @param amount  The amount to add to the specified account.
	 * @return Returns a String representing the modified account.
	 * @throws TransactionException
	 */
	public abstract String addFunds(int account, final double amount) throws TransactionException;

	/**
	 * Creates an account (and opens a bank account with zero balance) with the
	 * given credentials.
	 * 
	 * @param firstName - First name of user.
	 * @param lastName  - Last name of user.
	 * @param taxID     - unique taxID of user.
	 * @param username  - username for user to login with.
	 * @param password  - password for user to login with.
	 * @return String representation of newly created account (with account number
	 *         and $0 balance).
	 * @throws TransactionException
	 */
	public abstract String createAccount(final String firstName, final String lastName, final String taxID,
			final String username, final String password) throws TransactionException;

	/**
	 * Used to login a user. Afterwards this Bank Teller should be setup to make
	 * requests for the logged in user.
	 * 
	 * @param username - username of user
	 * @param password - password of user
	 * @throws TransactionException
	 */
	public abstract void userLogin(final String username, final String password) throws TransactionException;
}
